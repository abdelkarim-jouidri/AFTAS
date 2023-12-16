package com.example.aftas.Entities.DTOs.Ranking;

import com.example.aftas.Entities.Models.RankingKey;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewRankingDTO {
    private RankingKey id;
    private Integer rank;
    private Integer score;
}
