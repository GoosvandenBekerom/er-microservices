package com.s63d.account.service;

import com.s63d.account.domain.Role;
import com.s63d.account.domain.User;
import com.s63d.account.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

@Stateless
public class UserService {
    @Inject
    private UserRepository repo;
    @Inject
    private ValidationService validation;
    @Inject
    private RoleService roleService;

    public User getById(long id) {
        User user = repo.getById(id);
        if (user == null) throw repo.notFound(id);
        return user;
    }

    public User save(String firstname, String lastname, String email, String password) {
        if (!validation.notNull(firstname, lastname, password)) {
            throw new BadRequestException("Please fill in all fields");
        }
        if (!validation.validateEmail(email)){
            throw new BadRequestException("Please enter a valid email");
        }

        Role basicRole = roleService.save("basic", "A basic user"); // TODO: get real role
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        return repo.save(new User(firstname, lastname, email, hashed, basicRole));
    }
}
