package com.tartner.domain.password

import org.springframework.stereotype.Component
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import java.security.spec.KeySpec
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

@Component
public class PasswordService {

    fun createPasswordHash(keySpecification: KeySpec): ByteArray {
        try {
            val keyFactory = SecretKeyFactory.getInstance(SecretKeyAlgorithm)

            return keyFactory.generateSecret(keySpecification).encoded
        } catch (exception: NoSuchAlgorithmException) {
            throw UnableToCreatePasswordException(exception)
        } catch (exception: InvalidKeySpecException) {
            throw UnableToCreatePasswordException(exception)
        }

    }

    fun createKeySpecification(password: String, salt: ByteArray): PBEKeySpec {
        return PBEKeySpec(password.toCharArray(), salt, NumberOfHashingIterations, DesiredKeyLength)
    }

    companion object {
        // The higher the number of iterations the more
        // expensive computing the hash is for us and
        // also for an attacker.
        private val DesiredKeyLength = 256
        private val NumberOfHashingIterations = 65536
        public val SecretKeyAlgorithm: String = "PBKDF2WithHmacSHA1"
    }
}
