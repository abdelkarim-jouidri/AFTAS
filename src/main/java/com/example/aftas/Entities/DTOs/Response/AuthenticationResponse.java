package com.example.aftas.Entities.DTOs.Response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {
    private String token;
}
