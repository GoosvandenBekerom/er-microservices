package com.s63d.location.service;


import com.s63d.generic.DomainService;
import com.s63d.location.domain.Trip;
import com.s63d.location.repository.TripRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TripService extends DomainService<Trip,Long, TripRepository> {
    @Inject
    public TripService(TripRepository repo) { super(repo); }
    public TripService() { super(); }

}