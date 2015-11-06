package com.tartner.dancehours.domain.danceuser;

import com.tartner.ExpectException;
import com.tartner.dancehours.DanceHoursId;
import com.tartner.dancehours.domain.danceuser.external.CreateDanceUserCommand;
import com.tartner.dancehours.domain.danceuser.external.DanceUserAggregateQueryModel;
import com.tartner.dancehours.domain.danceuser.external.DanceUserCreatedEvent;
import com.tartner.dancehours.domain.danceuser.external.DanceUserEmailAlreadyExistsException;
import com.tartner.dancehours.domain.danceuser.external.DanceUserIdAlreadyExistsException;
import com.tartner.domain.password.PasswordSetEvent;
import com.tartner.domain.password.TestPasswordHolder;
import com.tartner.utilities.KFixtures;
import org.axonframework.domain.DomainEventStream;
import org.axonframework.test.FixtureConfiguration;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

//********************************************************************************************
//********************************************************************************************
//********************************************************************************************
// WARNING: Do not convert to kotlin yet, the newGivenWhenThenFixture doesn't work correctly
// or I'm doing something wrong
//********************************************************************************************
//********************************************************************************************
//********************************************************************************************

public class DanceUserAggregateTest {

    private static String UUIDString = "e78320a0-48b9-490a-8b1b-f5bcdcf34995";
    public static final UUID CreateUserId = UUID.fromString(UUIDString);
    public static final String CreateEmail = "bdavisx@yahoo.com";
    public static final String CreateFirstName = "Bill";
    public static final String CreateLastName = "Davis";
    public static final String CreatePassword = "notReal";

    private FixtureConfiguration fixture;
    private CreateDanceUserCommand createCommand;
    private DanceUserCreatedEvent createdEvent;
    private DanceUserAggregateQueryModel queryModelMock;
    private PasswordSetEvent passwordSetEvent;

    @Before
    public void setUp() throws Exception {
        //fixture = Fixtures.newGivenWhenThenFixture( DanceUserAggregate.class );
        fixture = KFixtures.INSTANCE.newGivenWhenThenFixture(DanceUserAggregate.class);
        createCommand = createValidCreateCommand();
        createdEvent = createCreatedEventForValidCommand(createCommand);
        queryModelMock = mock(DanceUserAggregateQueryModel.class);
        passwordSetEvent = createPasswordSetEvent();
    }

    @Test
    public void danceUserCreated() throws Exception {
        /* Note: We're going to test the command handler and the aggregate
            together because they act as a unit. The command handler should
            only be the container managed side of the unit, it shouldn't
            be doing anything else. */

        DanceUserAggregate user = new DanceUserAggregate();
        user.create(createCommand, queryModelMock, passwordSetEvent);

        verify(queryModelMock).emailAlreadyExists(CreateEmail);
        verify(queryModelMock).userIdAlreadyExists(buildCreateUserId());

        final DomainEventStream events = user.getUncommittedEvents();
        assertThat(createdEvent, equalTo(events.next().getPayload()));
        assertThat(passwordSetEvent, equalTo(events.next().getPayload()));
    }

    @NotNull
    private DanceHoursId buildCreateUserId() {return DanceHoursId.Default.create(CreateUserId);}

    @Test()
    public void danceUserCreatedDuplicateUserId() throws Exception {
        CreateDanceUserCommand command = createValidCreateCommand();

        DanceUserAggregateQueryModel queryModelMock = mock(DanceUserAggregateQueryModel.class);
        when(queryModelMock.userIdAlreadyExists(buildCreateUserId())).thenReturn(true);

        DanceUserAggregate user = new DanceUserAggregate();
        ExpectException.expectException(DanceUserIdAlreadyExistsException.class, () -> {
            user.create(createCommand, queryModelMock, passwordSetEvent);
        });
    }

    @Test
    public void danceUserCreatedDuplicateEmail() throws Exception {
        CreateDanceUserCommand command = createValidCreateCommand();

        DanceUserAggregateQueryModel queryModelMock =
            mock(DanceUserAggregateQueryModel.class);
        when(queryModelMock.emailAlreadyExists(CreateEmail))
            .thenReturn(true);

        DanceUserAggregate user = new DanceUserAggregate();

        ExpectException.expectException(DanceUserEmailAlreadyExistsException.class,
            () -> user.create(createCommand, queryModelMock, passwordSetEvent));
    }

    private CreateDanceUserCommand createValidCreateCommand() {
        CreateDanceUserCommand command =
            new CreateDanceUserCommand(buildCreateUserId(), CreateEmail,
                CreateLastName, CreateFirstName, CreatePassword, new HashSet<>());
        return command;
    }

    private DanceUserCreatedEvent createCreatedEventForValidCommand(
        final CreateDanceUserCommand command) {
        DanceUserCreatedEvent event = new DanceUserCreatedEvent(buildCreateUserId(),
            command.getEmail(), command.getLastName(), command.getFirstName(), command.getRoles());
        return event;
    }

    private PasswordSetEvent createPasswordSetEvent() {
        final TestPasswordHolder passwordHolder =
            TestPasswordHolder.Companion.CreateDefaultTest();
        return new PasswordSetEvent(createCommand.getUserId(),
            passwordHolder.getPasswordHash(), passwordHolder.getSalt());
    }

}
