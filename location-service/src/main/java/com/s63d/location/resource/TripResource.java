package com.s63d.location.resource;


import com.s63d.generic.JsonResource;
import com.s63d.location.domain.Trip;
import com.s63d.location.repository.TripRepository;
import com.s63d.location.service.TripService;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("trip")
public class TripResource extends JsonResource<Trip, Long, TripRepository, TripService> {

    @Inject
    public TripResource(TripService service) { super(service); }

}