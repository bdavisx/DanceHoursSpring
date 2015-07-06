package com.tartner.dancehours.domain.danceuser.external

import com.tartner.dancehours.domain.danceuser.DanceUserRole
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier
import java.util.UUID

public data class CreateDanceUserCommand(
    TargetAggregateIdentifier val userId: UUID,
    var email: String,
    var lastName: String,
    var firstName: String,
    var password: String,
    var roles: Set<DanceUserRole>
    )

