package com.tartner.dancehours.domain.danceuser.external;

public class DanceUserEmailAlreadyExistsException extends RuntimeException {
    private final String email;

    public DanceUserEmailAlreadyExistsException( final String email ) {
        this.email = email;
    }

    public String getEmail() { return email; }
}
