package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Ranking.CreateRankingDTO;
import com.example.aftas.Entities.DTOs.Ranking.RankingDTO;
import com.example.aftas.Entities.Models.Ranking;
import com.example.aftas.Entities.Models.RankingKey;
import com.example.aftas.Repositories.RankingRepository;
import com.example.aftas.Services.RankingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service("rankingServiceImpl")
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {
    private final ModelMapper modelMapper;
    private final RankingRepository rankingRepository;
    @Override
    public RankingDTO saveRanking(RankingDTO rankingDTO) {
        Ranking rankingEntity = modelMapper.map(rankingDTO, Ranking.class);
        return modelMapper.map(rankingRepository.save(rankingEntity), RankingDTO.class);
    }
}
