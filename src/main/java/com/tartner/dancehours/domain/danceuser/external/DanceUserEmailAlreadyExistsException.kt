package com.tartner.dancehours.domain.danceuser.external

public class DanceUserEmailAlreadyExistsException(public val email: String) : RuntimeException()
