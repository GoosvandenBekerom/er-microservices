package com.s63d.location.resource;


import com.s63d.generic.JsonResource;
import com.s63d.location.domain.Trip;
import com.s63d.location.repository.TripRepository;
import com.s63d.location.service.TripService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

@Path("trip")
public class TripResource extends JsonResource<Trip, Long, TripRepository, TripService> {

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
        return ok(service.getTripResponse(tripId)).build();
    }
}