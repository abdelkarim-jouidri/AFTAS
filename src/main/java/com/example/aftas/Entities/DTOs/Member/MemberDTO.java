package com.example.aftas.Entities.DTOs.Member;

import com.example.aftas.Entities.DTOs.Hunting.HuntingDTO;
import com.example.aftas.Entities.DTOs.Ranking.RankingDTO;
import com.example.aftas.Enums.IdentityDocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Integer num;
    private String firstname;
    private String lastname;
    private Date joiningDate;
    private String nationality;
    private IdentityDocumentType identityDocumentType;
    private String identityNumber;
    private Set<RankingDTO> rankings;
    private Set<HuntingDTO> huntings;
}