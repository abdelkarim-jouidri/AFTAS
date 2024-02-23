package com.example.aftas.Entities.Models;

import com.example.aftas.Enums.IdentityDocumentType;
import com.example.aftas.Enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;

    private String firstname;
    private String lastname;
    private Date joiningDate;
    private String nationality;
    private IdentityDocumentType identityDocumentType;
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    private Set<Ranking> rankings;

    @OneToMany(mappedBy = "member")
    private Set<Hunting> huntings;

    private Role role;
}
