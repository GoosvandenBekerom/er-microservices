package com.s63d.account.service;

import com.s63d.account.domain.Role;
import com.s63d.account.repository.RoleRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RoleService {
    @Inject
    private RoleRepository repo;

    public Role save(String name, String description) {
        return repo.save(new Role(name, description));
    }
}
