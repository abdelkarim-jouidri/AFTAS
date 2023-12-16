package com.example.aftas.Controllers;

import com.example.aftas.Entities.DTOs.Hunting.CreateHuntingDTO;
import com.example.aftas.Entities.DTOs.Hunting.HuntingDTO;
import com.example.aftas.Entities.DTOs.Hunting.ViewHuntingDTO;
import com.example.aftas.Entities.DTOs.Response.CustomResponse;
import com.example.aftas.Entities.Models.Hunting;
import com.example.aftas.Exceptions.CannotStoreHuntingException;
import com.example.aftas.Repositories.HuntingRepository;
import com.example.aftas.Services.HuntingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/huntings")
@RequiredArgsConstructor
@RestControllerAdvice
public class HuntingController {
    private final HuntingService huntingService;
    @PostMapping("/store")
    public ResponseEntity<CustomResponse<ViewHuntingDTO, String>> storeHunting(@Valid @RequestBody CreateHuntingDTO huntingDTO){
        try{
            ViewHuntingDTO savedHunting = huntingService.saveHunting(huntingDTO);
            CustomResponse<ViewHuntingDTO, String> response = new CustomResponse<>("Hunting saved successfully", savedHunting);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (CannotStoreHuntingException ex){
            throw ex;
        }catch (Exception ex){
            ex.printStackTrace();
            throw ex;
        }
    }
}
