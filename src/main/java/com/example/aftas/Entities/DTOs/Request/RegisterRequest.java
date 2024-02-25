package com.example.aftas.Entities.DTOs.Request;

import com.example.aftas.Enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank @NotNull
    private String email;
    @NotBlank @NotNull
    private String password;
    @NotBlank @NotNull
    private String firstName;
    @NotBlank @NotNull
    private String lastName;
    @NotBlank @NotNull
    private Role role;
}
