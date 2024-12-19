package com.gitDemo.movieLibraryDemo.auth.jwt;

import com.gitDemo.movieLibraryDemo.auth.config.AuthConfigProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtTokenService {

    private final AuthConfigProperties authConfigProperties;

    public JwtTokenService(AuthConfigProperties authConfigProperties) {
        this.authConfigProperties = authConfigProperties;
    }


    public String getUserNameFromToken(String jwtToken) {
        return extractClaims(jwtToken).getSubject();
    }

    public Date getExpirationDateFromToken(String jwtToken) {
        return extractClaims(jwtToken).getExpiration();
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        boolean isExpired = getExpirationDateFromToken(jwtToken).before(new Date());
        boolean isUserNameCorrect = getUserNameFromToken(jwtToken).equals(userDetails.getUsername());
        return isExpired;
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


