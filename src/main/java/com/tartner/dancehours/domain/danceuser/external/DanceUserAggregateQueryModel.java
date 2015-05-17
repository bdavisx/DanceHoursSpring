package com.tartner.dancehours.domain.danceuser.external;

import com.tartner.dancehours.querymodel.danceuser.DanceUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DanceUserAggregateQueryModel {
    @Autowired private DanceUserRepository repository;

    public DanceUserAggregateQueryModel() {}

    public DanceUserAggregateQueryModel( final DanceUserRepository repository) {
        this.repository = repository;
    }

    // Note: need to make this an interface that's implemented elsewhere,
    // this inverts the dependency, but for now, implement directly and then
    // refactor later

    /** Checks to see if the email is already in the sytem. */
    public boolean userIdAlreadyExists( UUID userId ) {
        return repository.exists( userId );
    }

    /** Checks to see if the email is already in the system. */
    public boolean emailAlreadyExists( String email ) {
        return repository.existsByEmail( email ) > 0;
    }
}
