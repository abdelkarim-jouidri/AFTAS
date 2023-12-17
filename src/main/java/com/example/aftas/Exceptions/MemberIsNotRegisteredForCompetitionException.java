package com.example.aftas.Exceptions;

public class MemberIsNotRegisteredForCompetitionException extends RuntimeException{
    public MemberIsNotRegisteredForCompetitionException() {
        super("Member is not registered for the competition");
    }
}
