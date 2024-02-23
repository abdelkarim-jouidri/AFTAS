package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Ranking.ViewRankingDTO;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Entities.Models.Ranking;
import com.example.aftas.Entities.Models.RankingKey;
import com.example.aftas.Repositories.CompetitionRepository;
import com.example.aftas.Repositories.RankingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RankingServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RankingRepository rankingRepository;

    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private RankingServiceImpl rankingService;

    @Test
    void calculateResult() {
        String competitionCode = "Kh-23-12-23";
        Competition mockCompetition = new Competition();
        mockCompetition.setCode(competitionCode);
        List<Ranking> rankings = new ArrayList<>();
        RankingKey key1 = new RankingKey(1, competitionCode);
        RankingKey key2 = new RankingKey(2, competitionCode);
        rankings.add(Ranking.builder().id(key1).score(100).build());
        rankings.add(Ranking.builder().id(key2).score(200).build());
        mockCompetition.setRankings(rankings);
        when(competitionRepository.findById(competitionCode)).thenReturn(Optional.of(mockCompetition));


        when(modelMapper.map(Mockito.any(), Mockito.eq(ViewRankingDTO.class)))
                .thenAnswer(invocation -> {
                    Ranking ranking = invocation.getArgument(0);
                    ViewRankingDTO dto = new ViewRankingDTO();
                    dto.setId(ranking.getId());
                    dto.setScore(ranking.getScore());
                    dto.setRank(ranking.getRank());
                    return dto;
                });

        when(rankingRepository.save(Mockito.any(Ranking.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Test
        List<ViewRankingDTO> result = rankingService.calculateResult(competitionCode);

        // Verify results
        assertEquals(rankings.size(), result.size());
        assertEquals(1, result.get(0).getRank());
        assertEquals(2, result.get(1).getRank());
        assertEquals(200, result.get(0).getScore());
        assertEquals(100, result.get(1).getScore());
    }


    @Test
    void calculateResult_InvalidCompetitionCode() {
        String invalidCompetitionCode = "invalidCode";
        when(competitionRepository.findById(invalidCompetitionCode)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            rankingService.calculateResult(invalidCompetitionCode);
        });

        String expectedErrorMessage = "No such competition with code : " + invalidCompetitionCode;
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}
