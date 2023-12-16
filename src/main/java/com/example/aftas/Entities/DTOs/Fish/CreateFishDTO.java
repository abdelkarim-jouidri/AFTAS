package com.example.aftas.Entities.DTOs.Fish;

import com.example.aftas.Entities.DTOs.Level.LevelDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFishDTO {
    @Size(min = 1, message = "Fish name cannot be empty")
    private String name;

    @NotNull(message = "Average weight cannot be null")
    private Double averageWeight;

    @NotNull(message = "Level code cannot be null")
    private String levelCode;
}