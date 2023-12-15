package com.example.aftas.Entities.DTOs.Ranking;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Member.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingDTO {
    private Integer rank;
    private Integer score;
    private MemberDTO member; // Assuming you have a MemberDTO class
    private CompetitionDTO competition; // Assuming you have a CompetitionDTO class
}