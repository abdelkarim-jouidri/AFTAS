package com.example.aftas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.aftas.Entities.DTOs.Ranking.ViewRankingDTO;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Entities.Models.Ranking;
import com.example.aftas.Repositories.CompetitionRepository;
import com.example.aftas.Repositories.RankingRepository;
import com.example.aftas.Services.Implementations.RankingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CompetitionScoreResultServiceTest1 {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RankingRepository rankingRepository;

    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private RankingServiceImpl rankingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateResult() {
        String competitionCode = "mor-07-01-24";

        // Mock data
        Competition competition = mock(Competition.class); // Change: Create a mock object

        competition.setCode(competitionCode);

        Ranking ranking1 = new Ranking();
        ranking1.setScore(100);

        Ranking ranking2 = new Ranking();
        ranking2.setScore(90);

        List<Ranking> rankings = Arrays.asList(ranking1, ranking2);

        // Mocking repository behavior
        when(competitionRepository.findById(competitionCode)).thenReturn(Optional.of(competition));
        when(competition.getRankings()).thenReturn(rankings);

        // Mock mapping
        when(modelMapper.map(any(), eq(ViewRankingDTO.class))).thenAnswer(invocation -> {
            Ranking inputRanking = invocation.getArgument(0);
            ViewRankingDTO viewRankingDTO = new ViewRankingDTO();
            viewRankingDTO.setScore(inputRanking.getScore());
            viewRankingDTO.setRank(inputRanking.getRank());
            // Map other properties as needed
            return viewRankingDTO;
        });

        // Call the method
        List<ViewRankingDTO> result = rankingService.calculateResult(competitionCode);

        // Verify the result
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getRank());
        assertEquals(2, result.get(1).getRank());

        // Verify interactions with the repository
        verify(rankingRepository, times(2)).save(any());
    }
}
