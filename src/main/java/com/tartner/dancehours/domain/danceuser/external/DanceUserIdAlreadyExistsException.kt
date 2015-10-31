package com.tartner.dancehours.domain.danceuser.external

import com.tartner.dancehours.DanceHoursId

public class DanceUserIdAlreadyExistsException(public val userId: DanceHoursId) : RuntimeException()
