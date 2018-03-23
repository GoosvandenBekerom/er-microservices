package com.s63d.account.resource;

import com.s63d.account.domain.Role;
import com.s63d.account.service.RoleService;
import com.s63d.annotation.Secured;
import com.s63d.generic.JsonResource;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("role")
@Secured("admin")
public class RoleResource extends JsonResource<Role, String, RoleService> {
    @Inject
    public RoleResource(RoleService service) { super(service); }

    @POST
    public Role save(@FormParam("name") String name, @FormParam("description") String description) {
        return service.save(name, description);
    }

    @Override
    public Response delete(String id) {
        return super.delete(id);
    }
}
