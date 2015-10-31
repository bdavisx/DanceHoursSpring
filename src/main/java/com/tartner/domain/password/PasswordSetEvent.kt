package com.tartner.domain.password

import com.tartner.dancehours.DanceHoursId

public data class PasswordSetEvent(
    public val aggregateId : DanceHoursId,
    public val passwordHash : ByteArray,
    public val salt : ByteArray)

