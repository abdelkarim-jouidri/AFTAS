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
public class HuntingDTO {
    private Integer id;
    private int numberOfFishes;
    private FishDTO fish; // Assuming you have a FishDTO class
    private MemberDTO member; // Assuming you have a MemberDTO class
    private CompetitionDTO competition; // Assuming you have a CompetitionDTO class
}