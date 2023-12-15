package com.example.aftas.Exceptions;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Response.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomResponse<Object, String>> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder errorMsg = new StringBuilder("Validation error : ");
        for (FieldError fieldError : fieldErrors){
            errorMsg.append("Field '").
                    append(fieldError.getField()).
                    append("' : ").
                    append(fieldError.getDefaultMessage()).
                    append(" ; ");
        }
        CustomResponse<Object, String> response = new CustomResponse<>(errorMsg.toString(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
}
