package com.example.aftas.Repositories;

import com.example.aftas.Entities.Models.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, String> {
    boolean existsByDate(Date date);
    Competition findCompetitionByCode(String code);
}
