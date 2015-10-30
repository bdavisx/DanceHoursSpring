package com.tartner.domain.password

import com.thoughtworks.xstream.XStream
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.*

public class TestPasswordDataGenerator(private val passwordService: PasswordService) {

    @throws(NoSuchAlgorithmException::class)
    public fun makePassword(password: String) {
        val salt = SecureRandom.getInstanceStrong().generateSeed(SaltLength)

        val keySpecification = passwordService.createKeySpecification(password, salt)

        val passwordHash = passwordService.createPasswordHash(keySpecification)
        val holder = TestPasswordHolder()
        holder.passwordHash = passwordHash
        holder.salt = salt

        val x = XStream()
        val passwordData = x.toXML(holder)
        println(passwordData)
    }

    private fun matches(expectedPasswordHash: ByteArray, passwordHash: ByteArray): Boolean {
        return Arrays.equals(expectedPasswordHash, passwordHash)
    }

    companion object {
        public fun main(args: Array<String>) {
            val service = PasswordService()
            val me = TestPasswordDataGenerator(service)
            try {
                me.makePassword("P@ssw0rd")
            } catch (e: NoSuchAlgorithmException) {
                throw RuntimeException(e)
            }

        }

        private val SaltLength = 32
    }

}

fun main(args: Array<String>) = TestPasswordDataGenerator.main(args)
