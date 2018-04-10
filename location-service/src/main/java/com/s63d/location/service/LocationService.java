package com.s63d.location.service;

import com.s63d.generic.DomainService;
import com.s63d.location.domain.Location;
import com.s63d.location.repository.LocationRepository;

import javax.ejb.Stateless;

@Stateless
public class LocationService extends DomainService<Location,Long, LocationRepository> {
}
