package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Ranking.CreateRankingDTO;
import com.example.aftas.Entities.DTOs.Ranking.RankingDTO;
import com.example.aftas.Entities.DTOs.Ranking.ViewRankingDTO;
import com.example.aftas.Entities.Models.Hunting;
import com.example.aftas.Entities.Models.Ranking;
import com.example.aftas.Entities.Models.RankingKey;

import java.util.List;

public interface RankingService {
    RankingDTO saveRanking(RankingDTO rankingDTO);
    Ranking updateScore(Hunting hunting);
    List<ViewRankingDTO> calculateResult(String code);
    List<ViewRankingDTO> calculatePodium(String code);
}
