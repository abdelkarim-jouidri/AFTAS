package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.CreateCompetitionDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface CompetitionService {
    List<CompetitionDTO> findAll();
    CompetitionDTO StoreCompetition(CreateCompetitionDTO competitionDTO);
}
