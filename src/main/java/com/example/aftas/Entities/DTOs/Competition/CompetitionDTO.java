package com.example.aftas.Entities.DTOs.Competition;

import com.example.aftas.Entities.DTOs.Hunting.HuntingDTO;
import com.example.aftas.Entities.DTOs.Ranking.RankingDTO;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

@Builder
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class CompetitionDTO {
    private String code;
    private Date date;
    private Time startTime;
    private Time endTime;
    private int numberOfParticipants;
    private String location;
    private double amount;
    private List<RankingDTO> rankings;
    private List<HuntingDTO> huntings;
}
