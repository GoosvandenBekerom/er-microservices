package vehicle.clients;

import com.s63d.util.ServiceURLs;
import vehicle.domain.SimpleUser;

import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;

@Stateless
public class AccountClient {
    public SimpleUser getUserById(long id) {
        return ClientBuilder.newClient()
                .target(ServiceURLs.ACCOUNT_SERVICE).path("user").path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON).get(SimpleUser.class);
    }
}
