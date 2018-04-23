package com.s63d.location.resource;

import com.s63d.generic.JsonResource;
import com.s63d.location.domain.Location;
import com.s63d.location.repository.LocationRepository;
import com.s63d.location.service.LocationService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("location")
public class LocationResource extends JsonResource<Location, Long, LocationRepository, LocationService> {

    @Inject
    public LocationResource(LocationService service) { super(service); }

}