package com.s63d.location.resource;

import com.s63d.generic.JsonResource;
import com.s63d.location.domain.Location;
import com.s63d.location.repository.LocationRepository;
import com.s63d.location.service.LocationService;

import javax.inject.Inject;

public class LocationResource extends JsonResource<Location, Long, LocationRepository, LocationService> {
    @Inject
    public LocationResource(LocationService service) { super(service); }

}