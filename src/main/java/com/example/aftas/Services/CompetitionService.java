package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.CreateCompetitionDTO;
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
}
