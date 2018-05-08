package com.s63d.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.s63d.annotation.Secured;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

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
            Long userId = jwt.getClaim("userId").asLong();
            String userRole = jwt.getClaim("userRole").asString();

            if (!isPermitted(userRole, allowed)) {
                return false;
            }

            requestContext.setProperty("user", userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isPermitted(String userRole, String[] allowed) {
        for(String role : allowed) {
            if (role.equals(userRole)) return true;
        }
        return false;
    }
}