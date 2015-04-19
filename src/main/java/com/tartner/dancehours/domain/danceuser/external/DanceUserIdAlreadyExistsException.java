package com.tartner.dancehours.domain.danceuser.external;

import java.util.UUID;

public class DanceUserIdAlreadyExistsException extends RuntimeException {
    private final UUID userId;

    public DanceUserIdAlreadyExistsException( final UUID userId ) {
        this.userId = userId;
    }

    public UUID getUserId() { return userId; }
}
