package com.infoservice.init.infothreads.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.util.Date;

@Component
public class JwtTokenProvider {
//    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userPrincipal.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, Base64Utils.encode(jwtSecret.getBytes()))
                .compact();
    }

    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
//            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
//            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
//            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
//            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
//            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
