package com.example.aftas.Entities.DTOs.Member;

import com.example.aftas.Enums.IdentityDocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberDTO {
    @NotBlank(message = "First name is required")
    private String firstname;

    @NotBlank(message = "Last name is required")
    private String lastname;
    @NotBlank(message = "Last name is required")
    private String email;
    @NotNull(message = "Joining date cannot be null")
    private Date joiningDate;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    @NotNull(message = "Identity document type cannot be null")
    private IdentityDocumentType identityDocumentType;

    @NotBlank(message = "Identity number is required")
    private String identityNumber;

    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "role is required")
    private String role;

}