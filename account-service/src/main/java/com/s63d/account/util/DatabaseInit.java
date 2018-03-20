package com.s63d.account.util;

import com.s63d.account.service.RoleService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class DatabaseInit {
    @Inject
    RoleService roleService;

    @PostConstruct
    void init() {
        roleService.save("basic", "A basic user");
        roleService.save("police", "An user with police permissions");
        roleService.save("government", "An user with government permissions");
        roleService.save("admin", "A system admin user");
    }
}
