package com.s63d.generic;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static javax.ws.rs.core.Response.ok;

@Produces(MediaType.APPLICATION_JSON)
public class JsonResource<T, TID, TREPO extends Repository<T, TID>, TSERVICE extends DomainService<T, TID, TREPO>> {
    public TSERVICE service;
    public JsonResource(TSERVICE service) { this.service = service; }
}
