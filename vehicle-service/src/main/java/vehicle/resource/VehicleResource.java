package vehicle.resource;

import vehicle.domain.SimpleUser;
import vehicle.domain.Vehicle;
import vehicle.repository.VehicleRepository;
import vehicle.service.VehicleService;
import com.s63d.annotation.Secured;
import com.s63d.generic.JsonResource;
import com.s63d.util.ServiceURLs;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Secured
@Path("vehicle")
public class VehicleResource extends JsonResource<Vehicle, String, VehicleRepository, VehicleService> {
    @Inject
    public VehicleResource(VehicleService service) { super(service); }

    @GET
    public List<Vehicle> allVehiclesOfUser(@Context ContainerRequestContext context) {
        SimpleUser user = getRequestUser(context);
        return service.getAllVehicles(user);
    }

    @POST
    public Vehicle save(
            @Context ContainerRequestContext context,
            @FormParam("license") String license, @FormParam("type") String type,
            @FormParam("brand") String brand, @FormParam("color") String color)
    {
        return service.save(license, type, brand, color);
    }

    private SimpleUser getRequestUser(ContainerRequestContext context) {
        String userId = "1"; //context.getProperty("user").toString();
        return ClientBuilder.newClient()
                .target(ServiceURLs.ACCOUNT_SERVICE).path("user").path(userId)
                .request(MediaType.APPLICATION_JSON).get(SimpleUser.class);
    }
}
