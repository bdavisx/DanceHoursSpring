package com.tartner.dancehours.domain.danceuser;

import com.tartner.dancehours.domain.danceuser.external.CreateDanceUserCommand;
import com.tartner.dancehours.domain.danceuser.external.DanceUserAggregateQueryModel;
import com.tartner.domain.password.PasswordEventFactory;
import com.tartner.domain.password.PasswordSetEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateDanceUserCommandHandler {
    @Autowired private DanceUserAggregateQueryModel queryModel;
    @Autowired private PasswordEventFactory passwordEventFactory;

    public CreateDanceUserCommandHandler() {}

    public CreateDanceUserCommandHandler(
        final DanceUserAggregateQueryModel queryModel,
        final PasswordEventFactory passwordEventFactory ) {
        this.queryModel = queryModel;
        this.passwordEventFactory = passwordEventFactory;
    }

    /* Note: Should the validation logic be in the CommandHandler or the
            Aggregate? I'm for having all of it in the Aggregate, because that
            keeps the validation all in one place. Sometimes you might use
            a private inner class to separate out the validation. In other instances,
            you'll need to pass in some kind of "rules engine" api or the like for
            complex/external validation. This is fine, but the engine api should be
            determined by the aggregate as an interface, then a specific implementation
            can complete it however.
         */
    /* Note: Should the aggregate or the command handler be "in charge" of the
        multiple events that can come from the command?

        If the two classes are really one component, then the handler is fine
        being in charge, but that kind of contradicts the above concept of all
        the validation being in the aggregate...

        If we're going to keep the logic in the aggregate, then we should simply
        pass on the command and the needed container services to the aggregate.

        For consistencies sake, I'm going w/ having it all in the aggregate.
        */
    @CommandHandler
    public void createDanceUser( final CreateDanceUserCommand command ) {
        DanceUserAggregate aggregate = new DanceUserAggregate();
        final PasswordSetEvent passwordSetEvent =
            createPasswordSetEvent( command );
        aggregate.create( command, queryModel, passwordSetEvent );
    }

    private PasswordSetEvent createPasswordSetEvent(
        final CreateDanceUserCommand command ) {
        // Note: we create the password event here to keep the dependenccy
        // out of the aggregate
        return passwordEventFactory.createPasswordSetEvent( command.getUserId(),
            command.getPassword() );
    }
}
