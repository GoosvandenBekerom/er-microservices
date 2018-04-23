package com.s63d.location.repository;

import com.s63d.generic.Repository;
import com.s63d.location.domain.Location;
import com.s63d.location.domain.Trip;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TripRepository extends Repository<Trip, Long> {
    public TripRepository() {
        super(Trip.class);
    }

    public List<Location> getLocations(Long tripId) {

        Query qur  = em.createQuery("SELECT l FROM Location l WHERE l.trip.id = :tripId ORDER BY l.id", Location.class);
        qur = qur.setParameter("tripId", tripId);
        return qur.getResultList();

    }
}
