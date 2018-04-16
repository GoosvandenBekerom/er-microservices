package com.s63d.location.repository;

import com.s63d.generic.Repository;
import com.s63d.location.domain.Trip;

import javax.ejb.Stateless;

@Stateless
public class TripRepository extends Repository<Trip, Long> {
    public TripRepository() {
        super(Trip.class);
    }
}
