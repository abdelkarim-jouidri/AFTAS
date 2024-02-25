package com.example.aftas.Services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public interface JwtService {
    String extractUsername(String jwt);
    Claims extractAllClaims(String jwt);
    boolean isTokenValid(String jwt, UserDetails userDetails);
    boolean isTokenExpired(String jwt);
    String generateToken(UserDetails userDetails);
}
