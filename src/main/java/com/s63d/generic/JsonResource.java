package com.s63d.generic;

import com.s63d.annotation.Secured;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

@Produces(MediaType.APPLICATION_JSON)
public class JsonResource<T, TID, TREPO extends Repository<T, TID>, TSERVICE extends DomainService<T, TID, TREPO>> {
    public TSERVICE service;
    public JsonResource(TSERVICE service) { this.service = service; }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") TID id) {
        return ok(service.getById(id)).build();
    }

    @DELETE
    @Secured
    @Path("{id}")
    public Response delete(@PathParam("id") TID id) {
        service.deleteById(id);
        return ok().build();
    }
}
