package com.s63d.vehicle.service;

import com.s63d.generic.DomainService;
import com.s63d.vehicle.domain.SimpleUser;
import com.s63d.vehicle.repository.SimpleUserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SimpleUserService extends DomainService<SimpleUser, Long, SimpleUserRepository> {
    @Inject
    public SimpleUserService(SimpleUserRepository repository) { super(repository); }
    public SimpleUserService() {}
}
