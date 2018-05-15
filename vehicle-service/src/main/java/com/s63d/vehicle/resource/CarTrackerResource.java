package com.s63d.vehicle.resource;

import com.s63d.annotation.Secured;
import com.s63d.generic.JsonResource;
import com.s63d.vehicle.domain.CarTracker;
import com.s63d.vehicle.repository.CarTrackerRepository;
import com.s63d.vehicle.service.CarTrackerService;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.ok;

@Path("tracker")
@Secured("government")
public class CarTrackerResource extends JsonResource<CarTracker, Long, CarTrackerRepository, CarTrackerService> {
    @Inject
    public CarTrackerResource(CarTrackerService service) { super(service); }

    @GET
    @Path("all")
    public List<CarTracker> getAll() {
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public CarTracker getById(@PathParam("id") long id) {
        return service.getById(id);
    }

    @POST
    public CarTracker create(@FormParam("id") long id) {
        return service.save(new CarTracker(id));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response verifyCar(@FormParam("carId") String carId, @FormParam("trackerId") long trackerId) {
        service.verifyCar(carId, trackerId);
        JsonObject json = Json.createObjectBuilder().add("message","Car verified successfully").build();
        return ok(json).build();
    }
}
