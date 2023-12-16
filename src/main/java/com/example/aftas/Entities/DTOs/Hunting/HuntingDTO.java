    package com.example.aftas.Entities.DTOs.Hunting;

    import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
    import com.example.aftas.Entities.DTOs.Fish.FishDTO;
    import com.example.aftas.Entities.DTOs.Member.MemberDTO;
    import lombok.*;

    @Setter @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class HuntingDTO {
        private Integer id;
        private int numberOfFishes;
        private FishDTO fish;
        private MemberDTO member;
        private CompetitionDTO competition;
    }