package com.example.aftas.Entities.DTOs.Ranking;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Member.MemberDTO;
import com.example.aftas.Entities.Models.RankingKey;
import lombok.*;

@Setter @Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingDTO {
    private RankingKey id;
    private Integer rank;
    private Integer score;
    private MemberDTO member;
    private CompetitionDTO competition;

}