package com.tartner.domain.password

import com.tartner.IntegrationTestCategory
import com.tartner.dancehours.DanceHoursId
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.annotation.Transactional
import java.util.*

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = arrayOf(StandardIntegrationTestConfiguration::class))
@ComponentScan(basePackages = arrayOf("com.tartner.domain"))
@Transactional
@TransactionConfiguration(defaultRollback = true)
@Category(IntegrationTestCategory::class)
public open class PasswordProjectorTest {
    @Autowired private var repository: AggregatePasswordRepository? = null

    @Test
    public fun checkEvent() {
        if( repository == null ) throw IllegalStateException("repository not initialized")

        val projector = PasswordProjector(repository!!)

        val holder = TestPasswordHolder.CreateDefaultTest()

        val userId = DanceHoursId.create(UUID.randomUUID())

        val event = PasswordSetEvent(userId, holder.passwordHash, holder.salt)
        projector.handle(event)

        val passwordsEntity = repository!!.findOne(userId)

        assertThat(passwordsEntity, notNullValue())

        assertThat<ByteArray>(passwordsEntity.passwordHash, `is`(holder.passwordHash))
        assertThat<ByteArray>(passwordsEntity.salt, `is`(holder.salt))
    }
}
