package com.codingShuttle.linkedIn.api_gatway.filters;

import com.codingShuttle.linkedIn.api_gatway.execption.UnauthorizedException;
import com.codingShuttle.linkedIn.api_gatway.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Vinay B R
 * @project Live LinkedIn
 * @package com.codingShuttle.linkedIn.api_gatway.filters
 * @since 15/10/2024 - 11:29 pm
 */

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
    public AuthenticationFilter(JwtService jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }

    private final JwtService jwtService;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getPath().toString();

            // Bypass authentication for login and signup routes
            if (path.contains("/login") || path.contains("/signUp")) {
                // Simply continue the filter chain without authentication
                return chain.filter(exchange);
            }

            // Get Authorization header
            List<String> authorizationHeaders = exchange.getRequest().getHeaders().get("Authorization");

            if (authorizationHeaders == null || authorizationHeaders.isEmpty()) {
                // Return 401 if the Authorization header is missing
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                // Throw an UnauthorizedException if the Authorization header is missing
                throw new UnauthorizedException("Authorization header is missing.");
            }

            String authorizationHeader = authorizationHeaders.get(0);

            // Check if the header contains a Bearer token
            if (!authorizationHeader.startsWith("Bearer ")) {
                // Return 401 if the Authorization header is missing
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                // Throw an UnauthorizedException if the Authorization header is missing
                throw new UnauthorizedException("Authorization is not Proper Check the Bearer token once.");
            }

            try {
                // Extract token from the Authorization header
                String token = authorizationHeader.substring(7);

                // Validate and extract user ID from the token
                Long userId = jwtService.getUserIdFromToken(token);

                // Add user ID to the request headers or we can mutate the request in any way
                /*After successfully authenticated the jwt token we can mutate the request headers or
                request body(if present) however we want*/
                exchange
                        .mutate()
                        .request(r->r.header("X-User-Id",userId.toString()))
                        .build();

                log.info("Authenticated request for user ID: " + userId);

            } catch (Exception e) {
                // Return 401 if the Authorization header is missing
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                // Throw an UnauthorizedException if the Authorization header is missing
                throw new UnauthorizedException("Authorization is not Proper Check the Bearer token once.");
            }

            // Proceed with the request
            log.info("return the exchange");
            return chain.filter(exchange);

        };
    }

    public static class  Config{

    }
}
