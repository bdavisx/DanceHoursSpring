package com.tartner.domain.password

import com.tartner.IntegrationTestCategory
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

    @Test @throws(Exception::class)
    public fun checkEvent() {
        if( repository == null ) throw IllegalStateException("repository not initialized")

        val projector = PasswordProjector(repository!!)

        val holder = TestPasswordHolder.CreateDefaultTest()

        val uuid = UUID.randomUUID()

        val event = PasswordSetEvent(uuid, holder.passwordHash, holder.salt)
        projector.handle(event)

        val passwordsEntity = repository!!.findOne(uuid)

        assertThat(passwordsEntity, notNullValue())

        assertThat<ByteArray>(passwordsEntity.passwordHash, `is`(holder.passwordHash))
        assertThat<ByteArray>(passwordsEntity.salt, `is`(holder.salt))
    }
}
