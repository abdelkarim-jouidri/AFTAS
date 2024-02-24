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
public class ViewMemberDTO {
    private Integer num;
    private String firstname;
    private String lastname;
    private Date joiningDate;
    private String nationality;
    private IdentityDocumentType identityDocumentType;
    private String identityNumber;
    private String email;
    private Role role;

}