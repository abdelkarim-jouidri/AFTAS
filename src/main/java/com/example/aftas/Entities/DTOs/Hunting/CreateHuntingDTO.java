package com.example.aftas.Entities.DTOs.Hunting;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Fish.FishDTO;
import com.example.aftas.Entities.DTOs.Member.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateHuntingDTO {
    private Double caughtFishWeight;
    private int numberOfFishes;
    private FishDTO fish;
    private MemberDTO member;
    private CompetitionDTO competition; 
}