package com.s63d.location.resource;


import com.s63d.generic.JsonResource;
import com.s63d.location.domain.Trip;
import com.s63d.location.repository.TripRepository;
import com.s63d.location.service.TripService;
import it.rambow.master.javautils.PolylineEncoder;
import it.rambow.master.javautils.Track;
import it.rambow.master.javautils.Trackpoint;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;

import static it.rambow.master.javautils.PolylineEncoder.createEncodings;
import static javax.ws.rs.core.Response.ok;

@Path("trip")
public class TripResource extends JsonResource<Trip, Long, TripRepository, TripService> {

    private PolylineEncoder encoder = new PolylineEncoder();

    @Inject
    public TripResource(TripService service) { super(service); }

    @POST
    public Trip createTrip(@FormParam("trackerId") Long trackerId) {
        Trip trip = new Trip(trackerId);
        service.save(trip);

        return trip;
    }


    @GET
    @Path("/a/{tripId}")
    public Response loadTrip(@PathParam("tripId") Long tripId) {
        Trip trip = service.getById(tripId);

        Track trk = new Track();

        trip.getLocations()
                .parallelStream()
                .map(location -> new Trackpoint(location.getLat(), location.getLon()))
                .forEach(trk::addTrackpoint);

        HashMap result = createEncodings(trk, 17, 1);

        return ok(Json.createObjectBuilder().add("tripId", trip.getId()).add("polyline", result.get("encodedPoints").toString()).build()).build();
    }
}