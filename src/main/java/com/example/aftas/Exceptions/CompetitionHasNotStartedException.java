package com.example.aftas.Exceptions;

import lombok.Getter;

import java.sql.Date;

@Getter
public class CompetitionHasNotStartedException extends RuntimeException{
    private final Date date;

    public CompetitionHasNotStartedException(Date date) {
        super("Competition with date "+date.toString()+" hasn't started Yet");
        this.date = date;
    }

}
