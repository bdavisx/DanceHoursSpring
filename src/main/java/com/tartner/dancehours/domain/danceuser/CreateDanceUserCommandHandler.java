package com.tartner.dancehours.domain.danceuser;

import com.tartner.dancehours.domain.danceuser.external.CreateDanceUserCommand;
import com.tartner.dancehours.domain.danceuser.external.DanceUserAggregateQueryModel;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateDanceUserCommandHandler {
    @Autowired
    private DanceUserAggregateQueryModel queryModel;

    /* Note: Should the validation logic be in the CommandHandler or the
            Aggregate? I'm for having all of it in the Aggregate, because that
            keeps the validation all in one place. Sometimes you might use
            a private inner class to separate out the validation. In other instances,
            you'll need to pass in some kind of "rules engine" api or the like for
            complex/external validation. This is fine, but the engine api should be
            determined by the aggregate as an interface, then a specific implementation
            can complete it however.
         */
    @CommandHandler
    public void createDanceUser( final CreateDanceUserCommand command ) {
        DanceUserAggregate aggregate = new DanceUserAggregate();
        aggregate.initialize( command.getUserId(), command.getEmail(),
            command.getLastName(), command.getFirstName(), queryModel );

    }
}
