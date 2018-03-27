package account.resource;

import account.domain.SimpleUser;
import account.domain.Vehicle;
import account.repository.VehicleRepository;
import account.service.VehicleService;
import com.s63d.annotation.Secured;
import com.s63d.generic.JsonResource;
import com.s63d.util.ServiceURLs;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Secured
@Path("vehicle")
public class VehicleResource extends JsonResource<Vehicle, String, VehicleRepository, VehicleService> {
    @Inject
    public VehicleResource(VehicleService service) { super(service); }

    @POST
    public Vehicle save(
            @Context ContainerRequestContext context,
            @FormParam("license") String license, @FormParam("type") String type,
            @FormParam("brand") String brand, @FormParam("color") String color)
    {
        return service.save(license, type, brand, color);
    }

    private SimpleUser getRequestUser(ContainerRequestContext context) {
        String userId = context.getProperty("userId").toString();
        return ClientBuilder.newClient().target(ServiceURLs.ACCOUNT_SERVICE).path("user").path(userId)
                .request(MediaType.APPLICATION_JSON).get(SimpleUser.class);
    }
}
