package com.example.aftas.Entities.DTOs.Ranking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRankingDTO {
    private Integer rank;
    private Integer score;
    private Long memberId; // Assuming you have a unique identifier for members, adjust the type accordingly
    private String competitionCode; // Assuming you have a unique identifier for competitions, adjust the type accordingly
}
