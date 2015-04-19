package com.tartner.dancehours.domain.danceuser;

import com.google.common.base.Preconditions;
import com.tartner.dancehours.events.danceuser.DanceUserCreatedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.List;
import java.util.UUID;

public class DanceUserAggregate extends AbstractAnnotatedAggregateRoot<UUID> {
    @AggregateIdentifier
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private long passwordHash;
    private List<DanceUserRole> userRoles;

    public void initalize( final UUID userId, final String email,
        final String lastName, final String firstName ) {
        Preconditions.checkNotNull( userId );
        Preconditions.checkNotNull( email );
        Preconditions.checkArgument( !email.isEmpty() );
        Preconditions.checkNotNull( lastName );
        Preconditions.checkArgument( !lastName.isEmpty() );
        Preconditions.checkNotNull( firstName );
        Preconditions.checkArgument( !firstName.isEmpty() );

        // todo: setup saga for email validation (optional based on settings)
        // todo: consider other validations

        // todo: check for same userId
        // todo: check for same email

        DanceUserCreatedEvent event = new DanceUserCreatedEvent();
        event.setUserId( userId );
        event.setEmail( email );
        event.setLastName( lastName );
        event.setFirstName( firstName );
        apply( event );
    }

    @EventSourcingHandler
    private void danceUserCreated( DanceUserCreatedEvent event ) {
        id = event.getUserId();
        email = event.getEmail();
        lastName = event.getLastName();
        firstName = event.getFirstName();
    }
}
