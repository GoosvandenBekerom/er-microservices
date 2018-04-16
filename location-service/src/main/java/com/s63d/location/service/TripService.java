package com.s63d.location.service;


import com.s63d.generic.DomainService;
import com.s63d.location.domain.Trip;
import com.s63d.location.repository.TripRepository;

import javax.ejb.Stateless;

@Stateless
public class TripService extends DomainService<Trip,Long, TripRepository> {
}