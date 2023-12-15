package com.example.aftas.Controllers;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.CreateCompetitionDTO;
import com.example.aftas.Entities.DTOs.Response.CustomResponse;
import com.example.aftas.Enums.Status;
import com.example.aftas.Exceptions.CompetitionAlreadyExistsException;
import com.example.aftas.Repositories.CompetitionRepository;
import com.example.aftas.Services.Implementations.CompetitionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<CompetitionDTO>, String>> fetchAll(){
        try {
            return new ResponseEntity<>(new CustomResponse<>("data", competitionService.findAll()), HttpStatus.OK);
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/all/{status}")
    public ResponseEntity<CustomResponse<List<CompetitionDTO>, String>> getCompetitionsByStatus(@PathVariable Status status){
        String msg = "Filtered Competitions by " + status.toString();
        List<CompetitionDTO> data = competitionService.filterByStatus(status);
        CustomResponse<List<CompetitionDTO>, String> response = new CustomResponse<>(msg, data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
