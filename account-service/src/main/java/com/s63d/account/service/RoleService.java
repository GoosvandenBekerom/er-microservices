package com.s63d.account.service;

import com.s63d.account.domain.Role;
import com.s63d.account.repository.RoleRepository;
import com.s63d.generic.DomainService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RoleService extends DomainService<Role, String> {
    @Inject
    public RoleService(RoleRepository repo) { super(repo); }
    public RoleService() { super(); }

    public Role save(String name, String description) {
        return super.save(new Role(name, description));
    }
}
