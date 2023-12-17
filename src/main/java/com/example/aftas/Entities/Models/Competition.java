package com.example.aftas.Entities.Models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
@Builder
public class Competition {
    @Id
    private String code;

    private Date date;
    private Time startTime;
    private Time endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;

    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "competition")
    private List<Hunting> huntings;
}
