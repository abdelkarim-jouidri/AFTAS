package com.example.aftas.Repositories;

import com.example.aftas.Entities.Models.Level;
import com.example.aftas.Entities.Models.Ranking;
import com.example.aftas.Entities.Models.RankingKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, RankingKey> {
}
