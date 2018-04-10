package com.s63d.location.repository;

import com.s63d.generic.Repository;
import com.s63d.location.domain.Location;

import javax.ejb.Stateless;

@Stateless
public class LocationRepository extends Repository<Location, Long> {
    public LocationRepository() {
        super(Location.class);
    }
}
