package com.tartner.dancehours.domain.danceuser

import com.google.common.base.Preconditions
import com.tartner.dancehours.domain.danceuser.external.*
import com.tartner.domain.password.EncodedPassword
import com.tartner.domain.password.PasswordSetEvent
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot
import org.axonframework.eventsourcing.annotation.AggregateIdentifier
import org.axonframework.eventsourcing.annotation.EventSourcingHandler
import java.util.HashSet
import java.util.UUID

public class DanceUserAggregate : AbstractAnnotatedAggregateRoot<UUID>() {
    AggregateIdentifier private var id: UUID? = null
    private var firstName: String? = null
    private var lastName: String? = null
    private var email: String? = null
    private var password: EncodedPassword? = null
    private val userRoles: List<DanceUserRole>? = null

    fun create(command: CreateDanceUserCommand, queryModel: DanceUserAggregateQueryModel,
        passwordSetEvent: PasswordSetEvent) {

        initialize(command.userId, command.email, command.lastName, command.firstName, queryModel)
        // TODO: any kind of validation here?
        apply(passwordSetEvent)

        // todo: setup saga for email validation (optional based on settings)


    }

    // TODO: create classes for first/last name/email; have already screwed them up,
    // this should make serialization go easier as well; look @ where else this s/b done
    private fun initialize(userId: UUID, email: String, lastName: String, firstName: String,
        queryModel: DanceUserAggregateQueryModel) {
        /* Note: do we want the "regular" or "container" parameters first?
            "regular": they are the more important part of the method.
         */
        validateInitialize(userId, email, lastName, firstName, queryModel)

        val event = DanceUserCreatedEvent( userId, email, lastName, firstName,
            HashSet<DanceUserRole>())
        apply(event)
    }

    EventSourcingHandler
    private fun danceUserCreated(event: DanceUserCreatedEvent) {
        id = event.userId
        email = event.email
        lastName = event.lastName
        firstName = event.firstName
    }

    EventSourcingHandler
    private fun passwordSetEvent(event: PasswordSetEvent) {
        // Note: not sure that we actually need to save this if we're getting
        // the login validation data from a projection. It'll get there
        // thru the projection class.
        password = EncodedPassword(event)
    }

    private fun validateInitialize(userId: UUID, email: String, lastName: String, firstName: String,
        queryModel: DanceUserAggregateQueryModel) {
        Preconditions.checkNotNull(userId)
        Preconditions.checkNotNull(email)
        Preconditions.checkArgument(!email.isEmpty())
        Preconditions.checkNotNull(lastName)
        Preconditions.checkArgument(!lastName.isEmpty())
        Preconditions.checkNotNull(firstName)
        Preconditions.checkArgument(!firstName.isEmpty())

        if (queryModel.emailAlreadyExists(email)) {
            throw DanceUserEmailAlreadyExistsException(email)
        }
        if (queryModel.userIdAlreadyExists(userId)) {
            throw DanceUserIdAlreadyExistsException(userId)
        }
    }

}
