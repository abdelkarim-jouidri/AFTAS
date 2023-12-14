package com.example.aftas.Repositories;

import com.example.aftas.Entities.Models.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, String> {
}
