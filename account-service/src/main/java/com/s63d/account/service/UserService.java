package com.s63d.account.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.s63d.account.domain.Role;
import com.s63d.account.domain.User;
import com.s63d.account.repository.UserRepository;
import com.s63d.generic.DomainService;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Stateless
public class UserService extends DomainService<User, Long, UserRepository> {
    @Inject
    private ValidationService validation;
    @Inject
    private RoleService roleService;

    private static final long WEEK_IN_SECONDS = 604800;

    @Inject
    public UserService(UserRepository repo) { super(repo); }
    public UserService() { super(); }

    public User save(String firstname, String lastname, String email, String password) {
        if (!validation.notNull(firstname, lastname, password)) {
            throw new BadRequestException("Please fill in all fields");
        }
        if (!validation.validateEmail(email)){
            throw new BadRequestException("Please enter a valid email");
        }

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        Role basicRole = roleService.getById("basic");
        return super.save(new User(firstname, lastname, email, hashed, basicRole));
    }

    public String loginUser(String email, String password) {
        User user = getByEmail(email);
        if (user == null) {
            throw new NotFoundException("User with email "+email+" not found.");
        }
        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new ClientErrorException("Wrong password", 401);
        }
        return generateToken(user);
    }

    private User getByEmail(String email) {
        return repo.findByEmail(email);
    }

    public User updateUser(long userId, String firstname, String lastname) {
        return repo.updateUser(userId, firstname, lastname);
    }

    private String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret"); // TODO: extract secret to config file
            return JWT.create()
                    .withExpiresAt(Date.from(new Date().toInstant().plusSeconds(WEEK_IN_SECONDS)))
                    .withClaim("userId", user.getId())
                    .withClaim("userRole", user.getRole().getName())
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (UnsupportedEncodingException | JWTCreationException e){
            throw new InternalServerErrorException("An error occurred during the creation of your authorization token.");
        }
    }
}
