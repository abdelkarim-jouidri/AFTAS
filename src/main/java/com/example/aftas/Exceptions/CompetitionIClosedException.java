package com.example.aftas.Exceptions;

import lombok.Getter;

import java.sql.Date;

@Getter
public class CompetitionIClosedException extends RuntimeException{
    private final Date date;

    public CompetitionIClosedException(Date date) {
        super("Competition with date"+date.toString()+" is closed");
        this.date = date;
    }

}
