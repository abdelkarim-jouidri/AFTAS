package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Ranking.CreateRankingDTO;
import com.example.aftas.Entities.DTOs.Ranking.RankingDTO;
import com.example.aftas.Entities.Models.Hunting;
import com.example.aftas.Entities.Models.Ranking;
import com.example.aftas.Entities.Models.RankingKey;

public interface RankingService {
    RankingDTO saveRanking(RankingDTO rankingDTO);
    Ranking updateScore(Hunting hunting);
}
