package com.s63d.account.resource;

import com.s63d.account.service.DomainService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
public class JsonResource<T, TID, TSERVICE extends DomainService<T, TID>> {
    TSERVICE service;
    JsonResource(TSERVICE service) { this.service = service; }

    @GET
    @Path("{id}")
    public T getById(@PathParam("id") TID id) {
        return service.getById(id);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") TID id) {
        service.deleteById(id);
        return Response.ok().build();
    }
}
