package com.example.aftas.Controllers;

import com.example.aftas.Entities.DTOs.Request.AuthenticationRequest;
import com.example.aftas.Entities.DTOs.Request.RegisterRequest;
import com.example.aftas.Entities.DTOs.Response.AuthenticationResponse;
import com.example.aftas.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        try {
            AuthenticationResponse authenticate = authenticationService.authenticate(request);
            return ResponseEntity.ok(authenticate);

        }catch (ResponseStatusException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/profile")
    public Authentication profile(Authentication authentication){
        System.out.println(authentication);
        return authentication;
    }
}
