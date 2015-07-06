package com.tartner.dancehours.domain.danceuser.external

import java.util.UUID

public class DanceUserIdAlreadyExistsException(public val userId: UUID) : RuntimeException()
