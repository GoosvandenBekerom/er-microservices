package com.s63d.vehicle.resource;

import com.s63d.vehicle.clients.AccountClient;
import com.s63d.vehicle.domain.SimpleUser;
import com.s63d.vehicle.domain.Vehicle;
import com.s63d.vehicle.repository.VehicleRepository;
import com.s63d.vehicle.service.OwnershipService;
import com.s63d.vehicle.service.SimpleUserService;
import com.s63d.vehicle.service.VehicleService;
import com.s63d.annotation.Secured;
import com.s63d.generic.JsonResource;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import java.util.List;

@Secured
@Path("vehicle")
public class VehicleResource extends JsonResource<Vehicle, String, VehicleRepository, VehicleService> {
    private OwnershipService ownershipService;
    private SimpleUserService userService;
    private AccountClient accountClient;
    @Inject
    public VehicleResource(
            VehicleService service, OwnershipService ownershipService,
            SimpleUserService userService, AccountClient accountClient) {
        super(service);
        this.ownershipService = ownershipService;
        this.userService = userService;
        this.accountClient = accountClient;
    }

    @GET
    public List<Vehicle> allVehiclesOfUser(@Context ContainerRequestContext context) {
        long userId = (long) context.getProperty("user");
        SimpleUser user = accountClient.getUserById(userId);
        return service.getAllVehicles(user);
    }

    @GET
    @Path("{userId}")
    public List<Vehicle> allVehiclesOfUser(@PathParam("userId") long userId) {
        SimpleUser user = userService.getById(userId);
        return service.getAllVehicles(user);
    }

    @POST
    public Vehicle save(
            @Context ContainerRequestContext context,
            @FormParam("license") String license, @FormParam("type") String type,
            @FormParam("brand") String brand, @FormParam("color") String color, @FormParam("weight") int weight)
    {
        long userId = (long) context.getProperty("user");
        SimpleUser owner = accountClient.getUserById(userId);
        Vehicle vehicle = service.save(license, type, brand, color, weight);
        ownershipService.create(owner, vehicle);
        return vehicle;
    }
}
