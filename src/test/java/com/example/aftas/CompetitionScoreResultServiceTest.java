package com.example.aftas;

import com.example.aftas.Entities.DTOs.Ranking.ViewRankingDTO;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Entities.Models.Ranking;
import com.example.aftas.Repositories.CompetitionRepository;
import com.example.aftas.Repositories.RankingRepository;
import com.example.aftas.Services.CompetitionService;
import com.example.aftas.Services.Implementations.RankingServiceImpl;
import com.example.aftas.Services.RankingService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CompetitionScoreResultServiceTest {
    @Mock
    private  CompetitionRepository competitionRepository;
    @Mock
    private RankingRepository rankingRepository;

    @InjectMocks
    private RankingServiceImpl rankingService;

    public CompetitionScoreResultServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

        @Test
        public void testCalculateResult(){
            String competitionCode = "kho-19-12-23";
            Competition competition = new Competition();
            competition.setCode(competitionCode);

            Ranking ranking1 = new Ranking();
            ranking1.setScore(200);

            Ranking ranking2 = new Ranking();
            ranking2.setScore(300);

            List<Ranking> rankings = new ArrayList<>();
            rankings.add(ranking1);
            rankings.add(ranking2);

            when(competitionRepository.findById(competitionCode)).thenReturn(Optional.of(competition));
            when(competition.getRankings()).thenReturn(rankings);
            when(rankingRepository.save(any())).thenReturn(ranking1);

            List<ViewRankingDTO> result = rankingService.calculateResult(competitionCode);

            assertEquals(2, result.size());
            assertEquals(1, result.get(0).getRank());
            assertEquals(2, result.get(1).getRank());

            verify(rankingRepository, times(2)).save(any());

        }
}
