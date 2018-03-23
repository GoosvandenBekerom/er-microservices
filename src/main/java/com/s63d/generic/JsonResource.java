package com.s63d.generic;

import com.s63d.annotation.Secured;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
public class JsonResource<T, TID, TSERVICE extends DomainService<T, TID>> {
    public TSERVICE service;
    public JsonResource(TSERVICE service) { this.service = service; }

    @GET
    @Path("{id}")
    public T getById(@PathParam("id") TID id) {
        return service.getById(id);
    }

    @DELETE
    @Secured
    @Path("{id}")
    public Response delete(@PathParam("id") TID id) {
        service.deleteById(id);
        return Response.ok().build();
    }
}
