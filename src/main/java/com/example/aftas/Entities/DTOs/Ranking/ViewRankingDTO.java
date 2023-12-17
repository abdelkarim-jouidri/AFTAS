package com.example.aftas.Entities.DTOs.Ranking;

import com.example.aftas.Entities.DTOs.Competition.ViewCompetitionDTO;
import com.example.aftas.Entities.DTOs.Member.ViewMemberDTO;
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
    private ViewMemberDTO member;
    private ViewCompetitionDTO competition;
}
