package com.example.aftas.Entities.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
@Builder
public class Competition {
    @Id
    @Pattern(regexp = "^[aA-zZ]{3}\\-[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}$", flags = Pattern.Flag.UNICODE_CASE)
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
