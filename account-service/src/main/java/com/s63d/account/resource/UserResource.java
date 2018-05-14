package com.s63d.account.resource;

import com.s63d.account.domain.User;
import com.s63d.account.repository.UserRepository;
import com.s63d.account.service.UserService;
import com.s63d.annotation.Secured;
import com.s63d.generic.JsonResource;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

@Path("/user")
public class UserResource extends JsonResource<User, Long, UserRepository, UserService> {
    @Inject
    public UserResource(UserService service) { super(service); }

    @GET
    @Path("{id}")
    public User getById(@PathParam("id") Long id) {
        return service.getById(id);
    }

    @POST
    public User registerUser(
            @FormParam("firstname") String firstname, @FormParam("lastname") String lastname,
            @FormParam("email") String email, @FormParam("password") String password,
            @FormParam("address") String address, @FormParam("postal") String postal,
            @FormParam("city") String city
    ) {
        return service.save(firstname, lastname, email, password, address, postal, city);
    }

    @POST
    @Path("login")
    public Response login(@FormParam("email") String email, @FormParam("password") String password) {
        String token = service.loginUser(email, password);
        JsonObject json = Json.createObjectBuilder().add("token", token).build();
        return ok(json).build();
    }

    @PUT
    @Secured
    public User updateUser(
            @Context ContainerRequestContext context,
            @FormParam("firstname") String firstname, @FormParam("lastname") String lastname,
            @FormParam("address") String address, @FormParam("postal") String postal,
            @FormParam("city") String city
    ) {
        long userId = (long) context.getProperty("user");
        return service.updateUser(userId, firstname, lastname, address, postal, city);
    }

    @PUT
    @Secured
    public Response changePassword(
            @Context ContainerRequestContext context,
            @FormParam("old") String old, @FormParam("new") String newPass, @FormParam("newAgain") String newPassAgain
    ) {
        long userId = (long) context.getProperty("user");
        service.changePassword(userId, old, newPass, newPassAgain);
        return ok().build();
    }
}
