package com.tartner.dancehours.domain.danceuser.external

import com.tartner.dancehours.querymodel.danceuser.DanceUserRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

public class DanceUserAggregateQueryModelTest {
    private var dao: DanceUserRepository? = null
    private var queryModel: DanceUserAggregateQueryModel? = null

    Before
    public fun setUp() {
        dao = mock(javaClass<DanceUserRepository>())
        queryModel = DanceUserAggregateQueryModel(dao as DanceUserRepository)
    }

    Test
    throws(Exception::class)
    public fun testEmailAlreadyExistsWhenExists() {
        `when`(dao!!.existsByEmail(TestEmail)).thenReturn(1L)

        assertThat(queryModel!!.emailAlreadyExists(TestEmail), `is`(true))
    }

    Test
    throws(Exception::class)
    public fun testEmailAlreadyExistsWhenDoesNotExist() {
        `when`(dao!!.existsByEmail(TestEmail)).thenReturn(0L)

        assertThat(queryModel!!.emailAlreadyExists(TestEmail), `is`(false))
    }

    companion object {

        public val TestEmail: String = "bill@tartner.com"
    }
}
