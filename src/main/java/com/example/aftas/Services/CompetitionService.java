package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.CreateCompetitionDTO;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Enums.Status;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface CompetitionService {
    List<CompetitionDTO> findAll();
    CompetitionDTO StoreCompetition(CreateCompetitionDTO competitionDTO);
    List<CompetitionDTO> filterByStatus(Status status);
    public boolean isRegistrationOpen(CompetitionDTO competitionDTO);
    public CompetitionDTO findCompetitionByCode(String code);
    public boolean isCompetitionOngoing(Competition competition);
    public boolean isCompetitionUpcoming(Competition competition);
    public boolean isCompetitionExpired(Competition competition);
}
