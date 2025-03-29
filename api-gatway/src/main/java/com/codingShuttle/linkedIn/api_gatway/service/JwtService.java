package com.codingShuttle.linkedIn.api_gatway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.api_gatway.service
 * @since 10/10/2024 - 12:10 am
 */
@Service
public class JwtService {

    // Inject the JWT secret key from the application properties
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    /**
     * Get the secret key to be used for JWT verification.
     * <p>
     * We load the secret key from the application properties and convert it to a
     * SecretKey using the UTF-8 charset.
     *
     * @return The secret key.
     */
    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Extracts the user ID from a given JWT token.
     *
     * This method parses the provided JWT token to extract claims and
     * retrieves the user ID from the token's subject field. It expects the token
     * to be signed with the secret key configured in the application properties.
     *
     * @param token the JWT token from which the user ID should be extracted.
     * @return the user ID contained in the token's subject as a Long.
     * @throws io.jsonwebtoken.JwtException if the token is invalid or cannot be parsed.
     */

    public Long getUserIdFromToken(String token){
        Claims claims= Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }
}
