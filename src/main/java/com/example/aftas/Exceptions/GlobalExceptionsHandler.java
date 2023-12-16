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
    public ResponseEntity<String> handleCompetitionAlreadyExistsException(CompetitionAlreadyExistsException ex){
        return new ResponseEntity<>("Competition with this date already exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CompetitionRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleCompetitionNoLongerAcceptsRegistrationException(CompetitionAlreadyExistsException ex){
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("test Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder errorMsg = new StringBuilder("Validation error : ");
        for (FieldError fieldError : fieldErrors){
            errorMsg.append("Field '").
                    append(fieldError.getField()).
                    append("' : ").
                    append(fieldError.getDefaultMessage()).
                    append(" ; ");
        }
        return new ResponseEntity<>(errorMsg.toString(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(CannotStoreLevelException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleLevelStorageException(CannotStoreLevelException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(CannotStoreHuntingException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<String> handleStoreHuntingException(CannotStoreHuntingException ex){
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
}
