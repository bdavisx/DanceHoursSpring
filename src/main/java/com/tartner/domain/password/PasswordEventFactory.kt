package com.tartner.domain.password

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.security.SecureRandom
import java.security.spec.KeySpec
import java.util.UUID
import javax.crypto.spec.PBEKeySpec

Component
public class PasswordEventFactory Autowired constructor(
    private val random: SecureRandom, private val passwordService: PasswordService) {

    public fun createPasswordSetEvent(aggregateId: UUID, password: String): PasswordSetEvent {
        val salt = random.generateSeed(SaltLength)
        val keySpecification = passwordService.createKeySpecification(password, salt)
        val passwordHash = passwordService.createPasswordHash(keySpecification)

        // store the salt with the password
        return PasswordSetEvent(aggregateId, passwordHash, salt)
    }

    private fun createPasswordHash(keySpecification: KeySpec): ByteArray {
        return passwordService.createPasswordHash(keySpecification)
    }

    private fun createKeySpecification(password: String, salt: ByteArray): PBEKeySpec {
        return passwordService.createKeySpecification(password, salt)
    }

    companion object {
        /* Note: Note why the right margin is so small, and where it came from
        (Python style document) - also fits well on most website code windows. */

        private val log = LoggerFactory.getLogger(javaClass<PasswordEventFactory>())

        private val SaltLength = 32
    }
}
