package com.s63d.location.service;


import com.s63d.generic.DomainService;
import com.s63d.location.domain.Location;
import com.s63d.location.domain.Trip;
import com.s63d.location.repository.TripRepository;
import it.rambow.master.javautils.Track;
import it.rambow.master.javautils.Trackpoint;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import java.util.HashMap;
import java.util.List;

import static it.rambow.master.javautils.PolylineEncoder.createEncodings;

@Stateless
public class TripService extends DomainService<Trip,Long, TripRepository> {
    @Inject
    public TripService(TripRepository repo) { super(repo); }
    public TripService() { super(); }



    public JsonObject getTripResponse(Long tripId) {

        //404 error handling
        getById(tripId);
        List<Location> locations = repo.getLocations(tripId);


        Track trk = new Track();
        locations
                .parallelStream()
                .map(location -> new Trackpoint(location.getLat(), location.getLon()))
                .forEachOrdered(trk::addTrackpoint);

        double length = 0;
        for (int i = 0; i < locations.size() - 2; i++) {
            length += distFrom(locations.get(i), locations.get(i + 1));
        }
        HashMap result = createEncodings(trk, 17, 1);

        return Json.createObjectBuilder()
                .add("tripId", tripId)
                .add("length", Math.ceil(length))
                .add("polyline", result.get("encodedPoints").toString())
                .build();
    }

    private static float distFrom(Location loc1, Location loc2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(loc2.getLat() -loc1.getLat());
        double dLng = Math.toRadians(loc2.getLon()-loc1.getLon());
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(loc1.getLat())) * Math.cos(Math.toRadians(loc2.getLat())) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return (float) (earthRadius * c);
    }
}