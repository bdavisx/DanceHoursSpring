package com.tartner.dancehours.domain.danceuser;

import com.tartner.dancehours.domain.danceuser.external.CreateDanceUserCommand;
import com.tartner.dancehours.domain.danceuser.external.DanceUserAggregateQueryModel;
import com.tartner.dancehours.domain.danceuser.external.DanceUserCreatedEvent;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.DomainEventStream;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DanceUserAggregateTest {

    public static final String CreateUserId =
        "e78320a0-48b9-490a-8b1b-f5bcdcf34995";
    public static final String CreateEmail = "bdavisx@yahoo.com";
    public static final String CreateFirstName = "Bill";
    public static final String CreateLastName = "Davis";
    public static final String CreatePassword = "notReal";

    private FixtureConfiguration fixture;
    private UUID userId;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture( DanceUserAggregate.class );
        userId = UUID.fromString( CreateUserId );
    }

    @Test
    public void danceUserCreated() throws Exception {
        /* Note: We're going to test the command handler and the aggregate
            together because they act as a unit. The command handler should
            only be the container managed side of the unit, it shouldn't
            be doing anything else. */
        CreateDanceUserCommand command = createValidCreateCommand();

        DanceUserCreatedEvent event =
            createCreatedEventForValidCommand( command );

        DanceUserAggregateQueryModel queryModelMock  =
            mock( DanceUserAggregateQueryModel.class );

        DanceUserAggregate user = new DanceUserAggregate();
        user.initialize( userId, CreateEmail, CreateLastName, CreateFirstName,
            queryModelMock );

        verify( queryModelMock ).emailAlreadyExists( CreateEmail );
        verify( queryModelMock ).userIdAlreadyExists( userId );

        final DomainEventStream events = user.getUncommittedEvents();
        final DomainEventMessage next = events.next();
        final Object payload = next.getPayload();
        assertThat( event, equalTo( payload ) );
    }

    private CreateDanceUserCommand createValidCreateCommand() {
        CreateDanceUserCommand command = new CreateDanceUserCommand( userId );
        command
            .email( CreateEmail )
            .firstName( CreateFirstName )
            .lastName( CreateLastName )
            .password( CreatePassword );
        return command;
    }

    private DanceUserCreatedEvent createCreatedEventForValidCommand(
        final CreateDanceUserCommand command ) {
        DanceUserCreatedEvent event = new DanceUserCreatedEvent();
        event.setUserId( userId );
        event.setEmail( command.getEmail() );
        event.setFirstName( command.getFirstName() );
        event.setLastName( command.getLastName() );
        return event;
    }

}
