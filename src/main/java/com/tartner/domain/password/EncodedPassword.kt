package com.tartner.domain.password

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity

public class EncodedPassword private constructor(
    private val passwordHash : ByteArray,
    private val salt : ByteArray ) : AbstractAnnotatedEntity() {

    public constructor (event: PasswordSetEvent) : this(event.passwordHash, event.salt ) {}

    // TODO: need to create passwordHash and Salt classes, make sure
    // those and this one are compatible w/ XStream; then change the
    // event to have an EncodedPassword instead of the separate properties

    companion object {
        val Invalid = EncodedPassword(ByteArray(0), ByteArray(0))
    }
}
