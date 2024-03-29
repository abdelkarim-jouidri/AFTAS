package com.example.aftas.Entities.DTOs.Member;

import com.example.aftas.Entities.DTOs.Hunting.ViewHuntingDTO;
import com.example.aftas.Entities.DTOs.Ranking.ViewRankingDTO;
import com.example.aftas.Enums.IdentityDocumentType;
import com.example.aftas.Enums.Role;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Setter @Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Integer num;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
    private Date joiningDate;
    private String nationality;
    private IdentityDocumentType identityDocumentType;
    private String identityNumber;
    private Set<ViewRankingDTO> rankings;
    private Set<ViewHuntingDTO> huntings;
}