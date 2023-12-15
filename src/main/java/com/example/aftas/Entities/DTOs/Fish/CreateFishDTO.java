package com.example.aftas.Entities.DTOs.Fish;

import com.example.aftas.Entities.DTOs.Level.LevelDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFishDTO {
    private String name;
    private Double averageWeight;
    private String levelCode;
}