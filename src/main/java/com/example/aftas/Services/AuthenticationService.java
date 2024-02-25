package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Request.AuthenticationRequest;
import com.example.aftas.Entities.DTOs.Request.RegisterRequest;
import com.example.aftas.Entities.DTOs.Response.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
