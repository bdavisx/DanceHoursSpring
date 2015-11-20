package com.tartner.dancehours.domain.danceuser.external

import com.tartner.dancehours.DanceHoursId
import com.tartner.dancehours.domain.danceuser.DanceUserRole

public data class DanceUserCreatedEvent(
    public val userId: DanceHoursId,
    public val email: String,
    public val fullName: String,
    public val roles: Set<DanceUserRole> )

