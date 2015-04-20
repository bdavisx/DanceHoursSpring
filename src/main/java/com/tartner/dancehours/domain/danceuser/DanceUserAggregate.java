package com.tartner.dancehours.domain.danceuser;

import com.google.common.base.Preconditions;
import com.tartner.dancehours.domain.danceuser.external.CreateDanceUserCommand;
import com.tartner.dancehours.domain.danceuser.external.DanceUserAggregateQueryModel;
import com.tartner.dancehours.domain.danceuser.external.DanceUserCreatedEvent;
import com.tartner.dancehours.domain.danceuser.external.DanceUserEmailAlreadyExistsException;
import com.tartner.dancehours.domain.danceuser.external.DanceUserIdAlreadyExistsException;
import com.tartner.utilities.password.EncodedPassword;
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
    private EncodedPassword password;
    private List<DanceUserRole> userRoles;

    public void create( final CreateDanceUserCommand command,
        final DanceUserAggregateQueryModel queryModel ) {
        initialize( command.getUserId(), command.getEmail(),
            command.getLastName(), command.getFirstName(), queryModel );
        // todo: setup saga for email validation (optional based on settings)


    }

    private void initialize( final UUID userId, final String email,
        final String lastName, final String firstName,
        DanceUserAggregateQueryModel queryModel ) {
        /* Note: do we want the "regular" or "container" parameters first?
            "regular": they are the more important part of the method.
         */
        validateInitialize( userId, email, lastName, firstName, queryModel );

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

    private void validateInitialize( final UUID userId, final String email,
        final String lastName, final String firstName,
        final DanceUserAggregateQueryModel queryModel ) {
        Preconditions.checkNotNull( userId );
        Preconditions.checkNotNull( email );
        Preconditions.checkArgument( !email.isEmpty() );
        Preconditions.checkNotNull( lastName );
        Preconditions.checkArgument( !lastName.isEmpty() );
        Preconditions.checkNotNull( firstName );
        Preconditions.checkArgument( !firstName.isEmpty() );

        if (queryModel.emailAlreadyExists( email )) {
            throw new DanceUserEmailAlreadyExistsException( email );
        }
        if (queryModel.userIdAlreadyExists( userId )) {
            throw new DanceUserIdAlreadyExistsException( userId );
        }
    }

}
