package com.tartner.dancehours.domain.danceuser.external;

import java.util.UUID;

public interface DanceUserAggregateQueryModel {
    /** Checks to see if the email is already in the sytem. */
    boolean userIdAlreadyExists( UUID userId );

    /** Checks to see if the email is already in the sytem. */
    boolean emailAlreadyExists( String email );
}
