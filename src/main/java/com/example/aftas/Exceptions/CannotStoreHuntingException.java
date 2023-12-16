package com.example.aftas.Exceptions;

public class CannotStoreHuntingException extends RuntimeException{
    public CannotStoreHuntingException(Double averageFishWeight) {
        super("Cannot store hunting record for a fish weight less than the average weight of : "+ averageFishWeight);
    }
}
