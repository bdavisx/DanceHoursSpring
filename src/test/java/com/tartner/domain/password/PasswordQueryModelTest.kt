package com.tartner.domain.password

import com.tartner.dancehours.querymodel.jpa.AggregatePasswordsEntity
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.security.NoSuchAlgorithmException
import java.util.*

public class PasswordQueryModelTest {

    private var passwordService: PasswordService? = null
    private var queryModel: PasswordQueryModel? = null
    private var repository: AggregatePasswordRepository? = null

    @Before
    @throws(NoSuchAlgorithmException::class)
    public fun setUp() {
        passwordService = PasswordService()
        repository = mock(javaClass<AggregatePasswordRepository>())
        queryModel = PasswordQueryModel(passwordService!!, repository!!)
    }

    @Test @throws(Exception::class)
    public fun testPasswordsMatch() {
        val id = UUID.randomUUID()

        val holder = TestPasswordHolder.CreateDefaultTest()

        val password = AggregatePasswordsEntity()
        password.aggregateId = id
        password.passwordHash = holder.passwordHash
        password.salt = holder.salt

        `when`(repository!!.findOne(id)).thenReturn(password)

        val passwordsMatch = queryModel!!.passwordsMatch(id, TestPassword)
        assertThat(passwordsMatch, `is`(true))
    }

    companion object {
        public val TestPassword: String = "P@ssw0rd"
        private val SaltLength = 32
    }
}
