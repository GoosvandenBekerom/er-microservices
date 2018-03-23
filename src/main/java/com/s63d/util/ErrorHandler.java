package com.s63d.util;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ErrorHandler implements ExceptionMapper<ClientErrorException> {
    @Override
    public Response toResponse(ClientErrorException e) {
        int status = e.getResponse().getStatus();

        JsonObject errorResponse = Json.createObjectBuilder()
                .add("status", status)
                .add("message", e.getMessage())
                .build();

        return Response.status(status)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
