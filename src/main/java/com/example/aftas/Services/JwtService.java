package com.example.aftas.Services;

import org.springframework.stereotype.Component;

@Component
public interface JwtService {
    String extractUsername(String jwt);
}
