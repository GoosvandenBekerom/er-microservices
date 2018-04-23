package vehicle.resource;

import com.s63d.annotation.Secured;
import com.s63d.generic.JsonResource;
import vehicle.clients.AccountClient;
import vehicle.domain.Ownership;
import vehicle.domain.SimpleUser;
import vehicle.repository.OwnershipRepository;
import vehicle.service.OwnershipService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import java.util.List;

@Secured
@Path("ownership")
public class OwnershipResource extends JsonResource<Ownership, Long, OwnershipRepository, OwnershipService> {
    private AccountClient accountClient;
    @Inject
    public OwnershipResource(OwnershipService service, AccountClient accountClient) {
        super(service);
        this.accountClient = accountClient;
    }

    @GET
    public List<Ownership> allOwnershipsOfUser(@Context ContainerRequestContext context) {
        long userId = (long) context.getProperty("user");
        SimpleUser user = accountClient.getUserById(userId);
        return service.getAllForUser(user);
    }
}
