package com.tartner.dancehours.domain.danceuser.external

import com.tartner.dancehours.domain.danceuser.DanceUserRole
import java.util.UUID

public data class DanceUserCreatedEvent(
    public val userId: UUID,
    public val email: String,
    public val lastName: String,
    public val firstName: String,
    public val roles: Set<DanceUserRole> )

