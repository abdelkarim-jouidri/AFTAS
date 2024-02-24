package com.example.aftas.Entities.DTOs.Request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequest {
    private String email;
    private String password;
}
