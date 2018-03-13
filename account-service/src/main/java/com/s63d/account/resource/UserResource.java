package com.s63d.account.resource;

import com.s63d.account.domain.User;
import com.s63d.account.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("user")
public class UserResource extends JsonResource {
    private UserService service;
    @Inject
    public UserResource(UserService service) { this.service = service; }

    @GET
    @Path("{id}")
    public User getUserById(@PathParam("id") long id) {
        return service.getById(id);
    }

    @POST
    public User registerUser(
            @FormParam("firstname") String firstname, @FormParam("lastname") String lastname,
            @FormParam("email") String email, @FormParam("password") String password
    ) {
        return service.save(firstname, lastname, email, password);
    }
}
