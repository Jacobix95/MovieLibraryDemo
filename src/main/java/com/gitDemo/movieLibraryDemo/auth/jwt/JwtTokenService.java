package com.gitDemo.movieLibraryDemo.auth.jwt;

import com.gitDemo.movieLibraryDemo.auth.config.AuthConfigProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;

public class JwtTokenService {

    private final AuthConfigProperties authConfigProperties;

    public JwtTokenService(AuthConfigProperties authConfigProperties) {
        this.authConfigProperties = authConfigProperties;
    }

    public String generateJwtToken(String username) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = now.plus(authConfigProperties.validity());

        return Jwts.builder()
                .subject(username)
                .issuedAt(new LdtToDateAdapter(now))
                .expiration(new LdtToDateAdapter(expiration))
                .signWith(getKey())
                .compact();
    }


    public String getUserNameFromToken(String jwtToken) {
        return extractClaims(jwtToken).getSubject();
    }

    public Date getExpirationDateFromToken(String jwtToken) {
        return extractClaims(jwtToken).getExpiration();
    }

    public boolean validateToken(String jwtToken) {
        return !getExpirationDateFromToken(jwtToken).before(new Date());
    }


    private Claims extractClaims(String jwtToken) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(authConfigProperties.secret().getBytes());
    }
}


