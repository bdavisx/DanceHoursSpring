package com.tartner.domain.password

import com.tartner.dancehours.querymodel.jpa.AggregatePasswordsEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.Arrays
import java.util.UUID

Component
public class PasswordQueryModel
@Autowired constructor(private val passwordService: PasswordService, private val repository: AggregatePasswordRepository) {

    public fun passwordsMatch(aggregateId: UUID, password: String): Boolean {
        val aggregatePassword: AggregatePasswordsEntity? = repository.findOne(aggregateId)

        if( aggregatePassword == null ) return false

        return passwordsMatch(password, aggregatePassword.passwordHash, aggregatePassword.salt)
    }

    public fun passwordsMatch(password: String, passwordHashToCompareAgainst: ByteArray,
        salt: ByteArray): Boolean {

        val keySpecification = passwordService.createKeySpecification(password, salt)
        val passwordHash = passwordService.createPasswordHash(keySpecification)
        return matches(passwordHashToCompareAgainst, passwordHash)
    }

    private fun matches(expectedPasswordHash: ByteArray, passwordHash: ByteArray): Boolean {
        return Arrays.equals(expectedPasswordHash, passwordHash)
    }
}
