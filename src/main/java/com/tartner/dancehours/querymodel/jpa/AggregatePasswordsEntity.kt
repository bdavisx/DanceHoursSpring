package com.tartner.dancehours.querymodel.jpa

import com.google.common.base.Objects
import org.hibernate.annotations.Type
import java.util.UUID
import javax.persistence.*

Entity
Table(name = "aggregate_passwords", schema = "public", catalog = "dance_hours")
public class AggregatePasswordsEntity {
    Id Type(type = "pg-uuid")
    Column(name = "aggregate_id", nullable = false, insertable = true, updatable = true)
    public var aggregateId: UUID? = null

    Basic
    Column(name = "password_hash", nullable = false, insertable = true, updatable = true)
    public var passwordHash: ByteArray? = null

    Basic
    Column(name = "salt", nullable = false, insertable = true, updatable = true)
    public var salt: ByteArray? = null

    override fun hashCode(): Int {
        return Objects.hashCode(aggregateId, passwordHash, salt)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {return true }
        if (o == null || javaClass != o.javaClass) { return false }

        val that = o as AggregatePasswordsEntity
        return Objects.equal(aggregateId, that.aggregateId) && Objects.equal(passwordHash,
            that.passwordHash) && Objects.equal(salt, that.salt)
    }
}
