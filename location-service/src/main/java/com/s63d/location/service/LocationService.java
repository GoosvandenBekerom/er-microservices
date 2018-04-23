package com.s63d.location.service;

import com.s63d.generic.DomainService;
import com.s63d.location.domain.Location;
import com.s63d.location.repository.LocationRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LocationService extends DomainService<Location,Long, LocationRepository> {
    @Inject
    public LocationService(LocationRepository repo) { super(repo); }
    public LocationService() { super(); }

}
