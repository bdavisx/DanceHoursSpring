package com.tartner.domain.password

import java.util.UUID

public data class PasswordSetEvent( public val aggregateId: UUID, public val passwordHash: ByteArray,
    public val salt: ByteArray )

