    package com.example.aftas.Entities.DTOs.Hunting;

    import com.example.aftas.Entities.DTOs.Competition.ViewCompetitionDTO;
    import com.example.aftas.Entities.DTOs.Fish.FishDTO;
    import com.example.aftas.Entities.DTOs.Fish.ViewFishDTO;
    import com.example.aftas.Entities.DTOs.Member.ViewMemberDTO;
    import lombok.*;

    @Setter @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class ViewHuntingDTO {
        private Integer id;
        private int numberOfFishes;
        private ViewFishDTO fish;
        private ViewMemberDTO member;
        private ViewCompetitionDTO competition;
    }