package com.example.aftas.Exceptions;

public class MemberAlreadyRegisteredForCompetitionException extends RuntimeException{
    public MemberAlreadyRegisteredForCompetitionException() {
        super("Member already registered for this competition");
    }
}
