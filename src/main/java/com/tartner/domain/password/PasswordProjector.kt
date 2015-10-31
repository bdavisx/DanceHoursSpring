package com.tartner.domain.password

import com.tartner.dancehours.querymodel.jpa.AggregatePasswordsEntity
import org.axonframework.eventhandling.annotation.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
public class PasswordProjector @Autowired constructor(
    private val repository: AggregatePasswordRepository) {

    @EventHandler
    public fun handle(event: PasswordSetEvent) {
        var passwordRecord: AggregatePasswordsEntity? = repository.findOne(event.aggregateId)

        if (passwordRecord == null) {
            passwordRecord = AggregatePasswordsEntity()
            passwordRecord.aggregateId = event.aggregateId.identifier
        }

        passwordRecord.passwordHash = event.passwordHash
        passwordRecord.salt = event.salt

        repository.save<AggregatePasswordsEntity>(passwordRecord)
    }
}
