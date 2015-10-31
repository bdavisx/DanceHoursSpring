package com.tartner.domain.password

import com.tartner.dancehours.DanceHoursId
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Test
import java.security.SecureRandom
import java.util.*

public class PasswordServiceTest {

    private var eventFactory: PasswordEventFactory? = null
    private var passwordService: PasswordService? = null

    @Before
    public fun setUp() {
        passwordService = PasswordService()
        eventFactory = PasswordEventFactory(SecureRandom.getInstance("SHA1PRNG"), passwordService!!)
    }

    @Test
    public fun createPasswordSetEvent() {
        val userUd = DanceHoursId.create(UUID.randomUUID())

        val encodedPassword = eventFactory!!.createPasswordSetEvent(userUd, TestPassword)

        assertThat(encodedPassword, notNullValue())
        assertThat(encodedPassword.hashCode(), notNullValue())
    }

    companion object {
        public val TestPassword: String = "P@ssw0rd"
    }

}
