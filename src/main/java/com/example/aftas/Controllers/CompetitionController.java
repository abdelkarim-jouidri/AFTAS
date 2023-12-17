package com.example.aftas.Controllers;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.CreateCompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.ViewCompetitionDTO;
import com.example.aftas.Entities.DTOs.Response.CustomResponse;
import com.example.aftas.Enums.Status;
import com.example.aftas.Exceptions.CompetitionAlreadyExistsException;
import com.example.aftas.Repositories.CompetitionRepository;
import com.example.aftas.Services.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/competitions/")
@RequiredArgsConstructor
@RestControllerAdvice
public class CompetitionController {
    @Qualifier("competitionServiceImpl")
    private final CompetitionService competitionService;
    private final CompetitionRepository competitionRepository;

    @PostMapping("create")
    public ResponseEntity<CustomResponse<CompetitionDTO, String>> createCompetition
            (
            @Valid  @RequestBody CreateCompetitionDTO competitionDTO
            )

    {
        try {
            CompetitionDTO storedCompetitionDTO = competitionService.StoreCompetition(competitionDTO);
            String msg = "competition successfully stored ";
            return new ResponseEntity<>(new CustomResponse<>(msg, storedCompetitionDTO), HttpStatus.CREATED);
        }catch (CompetitionAlreadyExistsException ex){
            throw ex;
        }catch (Exception ex){
            throw ex;
        }

    }



    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<ViewCompetitionDTO>, String>> fetchAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size) {
        try {
            List<ViewCompetitionDTO> competitionsPage = competitionService.findAllPaginated(page, size);
            return new ResponseEntity<>(new CustomResponse<>("Paginated Competitions", competitionsPage), HttpStatus.OK);
        } catch (Exception ex) {
            // Handle exceptions more gracefully in a production environment
            throw ex;
        }
    }

    @GetMapping("/all/{status}")
    public ResponseEntity<CustomResponse<List<CompetitionDTO>, String>> getCompetitionsByStatus(@PathVariable Status status){
        try{
            String msg = "Filtered Competitions by " + status.toString();
            List<CompetitionDTO> data = competitionService.filterByStatus(status);
            CustomResponse<List<CompetitionDTO>, String> response = new CustomResponse<>(msg, data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw ex;
        }
    }

}
