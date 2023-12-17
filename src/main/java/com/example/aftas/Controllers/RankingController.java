package com.example.aftas.Controllers;

import com.example.aftas.Entities.DTOs.Ranking.ViewRankingDTO;
import com.example.aftas.Entities.DTOs.Response.CustomResponse;
import com.example.aftas.Services.RankingService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RestControllerAdvice
@RequestMapping("api/rankings")
@RequiredArgsConstructor
public class RankingController {
    private final RankingService rankingService;

    @GetMapping("/{competitionCode}/results")
    public ResponseEntity<CustomResponse<List<ViewRankingDTO> , String>> showResults(@PathVariable String competitionCode){
        try{
            List<ViewRankingDTO> viewRankingDTO = rankingService.calculateResult(competitionCode);
            CustomResponse<List<ViewRankingDTO> , String> response = new CustomResponse<>("result of the competition", viewRankingDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw ex;
        }
    }
}
