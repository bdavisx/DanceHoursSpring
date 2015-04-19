package com.tartner.dancehours.domain.danceuser;

import com.tartner.dancehours.commands.danceuser.CreateDanceUserCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class CreateDanceUserCommandHandler {

    @CommandHandler
    public void createDanceUser( final CreateDanceUserCommand command ) {
        DanceUserAggregate aggregate = new DanceUserAggregate();
        aggregate.initalize( command.getUserId(), command.getEmail(),
            command.getLastName(), command.getFirstName() );

    }
}
