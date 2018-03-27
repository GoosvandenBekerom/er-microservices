package com.s63d.account.resource;

import com.s63d.account.domain.Ownership;
import com.s63d.account.domain.SimpleVehicle;
import com.s63d.account.domain.User;
import com.s63d.account.repository.UserRepository;
import com.s63d.account.service.UserService;
import com.s63d.annotation.Secured;
import com.s63d.generic.JsonResource;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("user")
public class UserResource extends JsonResource<User, Long, UserRepository, UserService> {
    @Inject
    public UserResource(UserService service) { super(service); }

    @POST
    public User registerUser(
            @FormParam("firstname") String firstname, @FormParam("lastname") String lastname,
            @FormParam("email") String email, @FormParam("password") String password
    ) {
        return service.save(firstname, lastname, email, password);
    }

    @POST
    @Path("login")
    public Response login(@FormParam("email") String email, @FormParam("password") String password) {
        String token = service.loginUser(email, password);
        JsonObject json = Json.createObjectBuilder().add("token", token).build();
        return Response.ok(json).build();
    }

    @GET
    @Secured({"police", "government", "admin"})
    @Path("{id}/ownership")
    public List<Ownership> getOwnerships(@PathParam("id") long id) {
        return service.getOwnerships(id);
    }
}
