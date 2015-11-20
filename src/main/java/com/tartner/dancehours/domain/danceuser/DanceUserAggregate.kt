package com.tartner.dancehours.domain.danceuser

import com.google.common.base.Preconditions
import com.tartner.dancehours.DanceHoursId
import com.tartner.dancehours.domain.danceuser.external.*
import com.tartner.domain.password.EncodedPassword
import com.tartner.domain.password.PasswordSetEvent
import com.tartner.utilities.Empty
import com.tartner.utilities.KPreconditions
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot
import org.axonframework.eventsourcing.annotation.AggregateIdentifier
import org.axonframework.eventsourcing.annotation.EventSourcingHandler
import java.util.*

public class DanceUserAggregate : AbstractAnnotatedAggregateRoot<DanceHoursId>() {
    @AggregateIdentifier private var id: DanceHoursId = DanceHoursId.Default.Empty
    private var fullName: String = String.Empty
    private var email: String = String.Empty
    private var password: EncodedPassword = EncodedPassword.Invalid
    private val userRoles: List<DanceUserRole> = ArrayList<DanceUserRole>()

    fun create(command: CreateDanceUserCommand, queryModel: DanceUserAggregateQueryModel,
        passwordSetEvent: PasswordSetEvent) {

        // TODO: change fullName to class, w/ it's own validation capabilities
        initialize(command.userId, command.email, command.fullName, queryModel)
        // TODO: any kind of validation here? I think not by us, we should have a different class
        // do validation
        apply(passwordSetEvent)

        // todo: setup saga for email validation (optional based on settings)


    }

    // TODO: create classes for first/last name/email; have already screwed them up,
    // this should make serialization go easier as well; look @ where else this s/b done
    private fun initialize(userId: DanceHoursId, email: String, fullName: String,
        queryModel: DanceUserAggregateQueryModel) {
        /* Note: do we want the "regular" or "container" parameters first?
            "regular": they are the more important part of the method.
         */
        validateInitialize(userId, email, fullName, queryModel)

        val event = DanceUserCreatedEvent( userId, email, fullName, HashSet<DanceUserRole>())
        apply(event)
    }

    @EventSourcingHandler
    private fun danceUserCreated(event: DanceUserCreatedEvent) {
        id = event.userId
        email = event.email
        fullName = event.fullName
    }

    @EventSourcingHandler
    private fun passwordSetEvent(event: PasswordSetEvent) {
        // Note: not sure that we actually need to save this if we're getting
        // the login validation data from a projection. It'll get there
        // thru the projection class.
        password = EncodedPassword(event)
    }

    private fun validateInitialize(userId : DanceHoursId, email : String, fullName : String,
        queryModel : DanceUserAggregateQueryModel) {
        Preconditions.checkArgument(!userId.equals(DanceHoursId.Empty), "No userId supplied")
        KPreconditions.checkNotEmpty(email, "email was empty")
        Preconditions.checkArgument(!fullName.isEmpty(), "Name was empty")
        Preconditions.checkArgument(fullName.length <= MaximumNameSize, "Name too long")

        if (queryModel.emailAlreadyExists(email)) {
            throw DanceUserEmailAlreadyExistsException(email)
        }
        if (queryModel.userIdAlreadyExists(userId)) {
            throw DanceUserIdAlreadyExistsException(userId)
        }
    }

    companion object {
        private val MaximumNameSize = 1024
    }
}

