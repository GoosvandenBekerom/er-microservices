package com.s63d.account.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.s63d.account.annotation.Secured;
import com.s63d.account.domain.User;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

@Secured
@Provider
public class SecuredFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    @Context
    ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);
        String authToken = authHeader != null ? authHeader.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "") : "";

        Secured annotation = resourceInfo.getResourceMethod().getAnnotation(Secured.class);
        if (annotation == null) {
            annotation = resourceInfo.getResourceClass().getAnnotation(Secured.class);
        }

        if (annotation != null) {
            if (!verifyToken(authToken, requestContext, annotation.value()))
                throw new NotAuthorizedException("You are not authorized to do this request");
        }
        else throw new InternalServerErrorException("Something went wrong");
    }

    private Boolean verifyToken(String token, ContainerRequestContext requestContext, String... allowed) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret"); // TODO: extract secret to config file
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();

            DecodedJWT jwt = verifier.verify(token);
            User user = JsonbBuilder.create().fromJson(jwt.getClaim("user").asString(), User.class);

            if (!isPermitted(user, allowed)) {
                return false;
            }

            requestContext.setProperty("user", user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isPermitted(User user, String[] allowed) {
        for(String role : allowed) {
            if (role.equals(user.getRole().getName())) return true;
        }
        return false;
    }
}