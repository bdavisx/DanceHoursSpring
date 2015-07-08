package com.tartner.domain.password

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity

public class EncodedPassword(event: PasswordSetEvent) : AbstractAnnotatedEntity() {

    // TODO: need to create passwordHash and Salt classes, make sure
    // those and this one are compatible w/ XStream; then change the
    // event to have an EncodedPassword instead of the separate properties
    private val passwordHash: ByteArray
    private val salt: ByteArray

    init {
        passwordHash = event.passwordHash
        salt = event.salt
    }

}
