package com.example.aftas.Controllers;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.CreateCompetitionDTO;
import com.example.aftas.Entities.DTOs.Response.CustomResponse;
import com.example.aftas.Exceptions.CompetitionAlreadyExistsException;
import com.example.aftas.Repositories.CompetitionRepository;
import com.example.aftas.Services.Implementations.CompetitionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("api/competitions/")
@RequiredArgsConstructor
@RestControllerAdvice
public class CompetitionController {
    private final CompetitionServiceImpl competitionService;
    private final CompetitionRepository competitionRepository;

    @PostMapping("create")
    public ResponseEntity<CustomResponse<CompetitionDTO, String>> createCompetition
            (
            @RequestBody CreateCompetitionDTO competitionDTO
            )

    {
        try {

            CompetitionDTO storedCompetitionDTO = competitionService.StoreCompetition(competitionDTO);
            return new ResponseEntity<>(new CustomResponse<>("competition successfully stored ", storedCompetitionDTO), HttpStatus.CREATED);

        }catch (CompetitionAlreadyExistsException ex){
            throw ex;
        }catch (Exception ex){
            throw ex;
        }

    }

    @GetMapping("/exists")
    public ResponseEntity<String> checkIfCompetitionExists(@RequestParam Date date){
        return new ResponseEntity<>(String.valueOf(competitionRepository.existsByDate(date)), HttpStatus.OK);
    }

}
