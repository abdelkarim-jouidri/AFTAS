package com.example.aftas.Exceptions;

import lombok.Getter;

import java.sql.Date;

@Getter
public class CompetitionHasExpiredException extends RuntimeException{
    private final Date date;

    public CompetitionHasExpiredException(Date date) {
        super("Competition with date "+date.toString()+" has expired");
        this.date = date;
    }

}
