package com.s63d.account.resource;

import com.s63d.account.domain.User;
import com.s63d.account.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("user")
public class UserResource extends JsonResource<User, Long, UserService> {
    @Inject
    public UserResource(UserService service) { super(service); }

    @POST
    public User registerUser(
            @FormParam("firstname") String firstname, @FormParam("lastname") String lastname,
            @FormParam("email") String email, @FormParam("password") String password
    ) {
        return service.save(firstname, lastname, email, password);
    }
}
