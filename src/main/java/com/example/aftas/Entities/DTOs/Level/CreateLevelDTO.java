package com.example.aftas.Entities.DTOs.Level;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLevelDTO {
    private String description;
    private Integer points;
}