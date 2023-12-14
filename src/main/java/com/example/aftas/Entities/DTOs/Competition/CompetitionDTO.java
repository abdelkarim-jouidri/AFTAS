package com.example.aftas.Entities.DTOs.Competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
@Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class CompetitionDTO {
    private String code;
    private Date date;
    private Time startTime;
    private Time endTime;
    private int numberOfParticipants;
    private String location;
    private double amount;
}
