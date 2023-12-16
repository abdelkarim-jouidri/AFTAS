package com.example.aftas.Exceptions;

import lombok.Getter;

import java.sql.Date;

@Getter
public class CompetitionAlreadyExistsException extends RuntimeException{
    private final Date date;

    public CompetitionAlreadyExistsException(Date date) {
        super("Competition with date "+date.toString()+" already exists");
        this.date = date;
    }

}
