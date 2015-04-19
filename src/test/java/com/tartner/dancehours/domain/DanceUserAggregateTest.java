package com.tartner.dancehours.domain;

import com.tartner.dancehours.commands.danceuser.CreateDanceUserCommand;
import com.tartner.dancehours.domain.danceuser.DanceUserAggregate;
import com.tartner.dancehours.events.danceuser.DanceUserCreatedEvent;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.DomainEventStream;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DanceUserAggregateTest {

    public static final String CreateUserId =
        "e78320a0-48b9-490a-8b1b-f5bcdcf34995";
    public static final String CreateEmail = "bdavisx@yahoo.com";
    public static final String CreateFirstName = "Bill";
    public static final String CreateLastName = "Davis";
    public static final String CreatePassword = "notReal";
    private FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture( DanceUserAggregate.class );
    }

    @Test
    public void testCreateToDoItem() throws Exception {
        UUID userId = UUID.fromString( CreateUserId );

        CreateDanceUserCommand command = new CreateDanceUserCommand( userId );
        command
            .email( CreateEmail )
            .firstName( CreateFirstName )
            .lastName( CreateLastName )
            .password( CreatePassword );

        DanceUserCreatedEvent event = new DanceUserCreatedEvent();
        event.setUserId( userId );
        event.setEmail( command.getEmail() );
        event.setFirstName( command.getFirstName() );
        event.setLastName( command.getLastName() );

        DanceUserAggregate user = new DanceUserAggregate();
        user.initalize( userId, CreateEmail, CreateLastName, CreateFirstName );

        final DomainEventStream events = user.getUncommittedEvents();
        final DomainEventMessage next = events.next();
        final Object payload = next.getPayload();
        assertThat( event, equalTo( payload ) );
    }
}
