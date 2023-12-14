package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;

import java.util.List;

public interface CompertitionService {
    List<CompetitionDTO> findAll();
}
