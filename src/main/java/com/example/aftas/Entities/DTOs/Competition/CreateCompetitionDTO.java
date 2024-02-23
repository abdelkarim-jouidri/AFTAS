package com.example.aftas.Entities.DTOs.Competition;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class CreateCompetitionDTO {
    @NotNull(message = "date of the competition cannot be empty")
    @Future(message = "input date shouldn't be before today's date")
    private Date date;



    @NotNull(message = "Start time is required")
    private Time startTime;

    @NotNull(message = "End time is required")
    private Time endTime;

    @Min(value = 1, message = "Number of participants must be greater than zero")
    private int numberOfParticipants;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Amount is required")
    private double amount;
}
