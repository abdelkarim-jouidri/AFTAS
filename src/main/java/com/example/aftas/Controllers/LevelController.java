package com.example.aftas.Controllers;

import com.example.aftas.Entities.DTOs.Level.CreateLevelDTO;
import com.example.aftas.Entities.DTOs.Level.LevelDTO;
import com.example.aftas.Entities.DTOs.Response.CustomResponse;
import com.example.aftas.Exceptions.CannotStoreLevelException;
import com.example.aftas.Services.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("api/levels")
public class LevelController {
    private final LevelService levelService;

    @PostMapping("/create")
    public ResponseEntity<CustomResponse<LevelDTO, String>> storeLevel(@RequestBody CreateLevelDTO levelDTO){
        try{
            LevelDTO storedLevel = levelService.storeLevel(levelDTO);
            CustomResponse<LevelDTO, String> response = new CustomResponse<>("level stored successfully", storedLevel);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (CannotStoreLevelException ex){
            throw ex;
        }catch (Exception ex){
            throw ex;
        }
    }

}
