package com.example.aftas.Exceptions;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Response.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(CompetitionAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomResponse<CompetitionDTO, String>> handleCompetitionAlreadyExistsException(CompetitionAlreadyExistsException ex){
        return new ResponseEntity<>(new CustomResponse<>("Competition with this date already exists", null),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomResponse<CompetitionDTO, String>> handleException(Exception ex) {
        return new ResponseEntity<>(new CustomResponse<>("Internal Server Error: " + ex.getMessage() , null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
