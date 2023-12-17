package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Ranking.RankingDTO;
import com.example.aftas.Entities.DTOs.Ranking.ViewRankingDTO;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Entities.Models.Hunting;
import com.example.aftas.Entities.Models.Ranking;
import com.example.aftas.Entities.Models.RankingKey;
import com.example.aftas.Exceptions.NonSufficientNumberOfParticipantsException;
import com.example.aftas.Repositories.CompetitionRepository;
import com.example.aftas.Repositories.RankingRepository;
import com.example.aftas.Services.RankingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("rankingServiceImpl")
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {
    private final ModelMapper modelMapper;
    private final RankingRepository rankingRepository;
    private final CompetitionRepository competitionRepository;
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
            Ranking newRankingInstance = Ranking.
                        builder().
                        score(score).
                        id(key).
                        build();
            ranking = rankingRepository.save(newRankingInstance);
        }
        return ranking;
    }

    @Override
    public List<ViewRankingDTO> calculateResult(String code) {
        Optional<Competition> optionalCompetition = competitionRepository.findById(code);
        if (optionalCompetition.isEmpty()){
            throw new NoSuchElementException("No such competition with code : "+ code);
        }
        List<Ranking> rankings = optionalCompetition.get().getRankings();
        Collections.sort(rankings, Comparator.comparingInt(Ranking::getScore).reversed());
        IntStream.range(0, rankings.size()).
                forEach(index-> rankings.get(index).setRank(index+1));
        return rankings.stream().map(ranking -> modelMapper.map(rankingRepository.save(ranking), ViewRankingDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ViewRankingDTO> calculatePodium(String code) {
        List<ViewRankingDTO> rankingResult = calculateResult(code);
        if (rankingResult.size()<3){
            throw new NonSufficientNumberOfParticipantsException(rankingResult.size());
        }
        return rankingResult.subList(0,3);
    }
}
