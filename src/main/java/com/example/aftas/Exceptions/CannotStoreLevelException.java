package com.example.aftas.Exceptions;

public class CannotStoreLevelException extends RuntimeException{
    public CannotStoreLevelException(int highestLevelPoints) {
        super("Cannot store level with points less than or equals : "+ highestLevelPoints);
    }
}
