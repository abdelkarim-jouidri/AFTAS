package com.example.aftas.Exceptions;

public class NonSufficientNumberOfParticipantsException extends RuntimeException{
    public NonSufficientNumberOfParticipantsException(int size) {
        super("competition only has "+size+" participants");
    }
}
