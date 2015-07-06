package com.tartner.dancehours.domain.danceuser.external

import com.tartner.dancehours.domain.danceuser.DanceUserRole
import java.util.UUID

public data class DanceUserCreatedEvent(
    val userId: UUID,
    val email: String,
    val lastName: String,
    val firstName: String,
    val roles: Set<DanceUserRole> )

