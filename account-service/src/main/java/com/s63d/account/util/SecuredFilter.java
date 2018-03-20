package com.s63d.account.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.s63d.account.annotation.Secured;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Secured
@Provider
public class SecuredFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);
        String authToken = authHeader != null ? authHeader.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "") : "";

        if (!verifyToken(authToken, requestContext))
            throw new NotAuthorizedException("You are not authorized to do this request");
    }

    private Boolean verifyToken(String token, ContainerRequestContext requestContext) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret"); // TODO: extract secret to config file
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();

            DecodedJWT jwt = verifier.verify(token);
            requestContext.setProperty("user", jwt.getClaim("user").asString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}