package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Ranking.RankingDTO;
import com.example.aftas.Entities.Models.Hunting;
import com.example.aftas.Entities.Models.Ranking;
import com.example.aftas.Entities.Models.RankingKey;
import com.example.aftas.Repositories.RankingRepository;
import com.example.aftas.Services.RankingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Ranking updateScore(Hunting hunting) {
        Ranking ranking = null;
        RankingKey key = new RankingKey(hunting.getMember().getNum(), hunting.getCompetition().getCode());
        Optional<Ranking> associatedRanking = rankingRepository.findById(key);
        int score = hunting.getNumberOfFishes() * hunting.getFish().getLevel().getPoints();
        if (associatedRanking.isPresent()){
            associatedRanking.get().setScore(score);
            ranking = rankingRepository.save(associatedRanking.get());
        }else {
            Ranking newRankingInstance = Ranking.builder().score(score).id(key).build();
            ranking = rankingRepository.save(newRankingInstance);
        }
        return ranking;
    }
}
