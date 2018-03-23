package com.s63d.account.repository;

import com.s63d.account.domain.Role;
import com.s63d.generic.Repository;

import javax.ejb.Stateless;

@Stateless
public class RoleRepository extends Repository<Role, String> {
    public RoleRepository() { super(Role.class); }
}
