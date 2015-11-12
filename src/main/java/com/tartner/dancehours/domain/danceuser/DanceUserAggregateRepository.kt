package com.tartner.dancehours.domain.danceuser

import org.axonframework.eventhandling.EventBus
import org.axonframework.eventsourcing.EventSourcingRepository
import org.axonframework.eventstore.EventStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component open class DanceUserAggregateRepository @Autowired constructor(eventStore: EventStore)
    : EventSourcingRepository<DanceUserAggregate>(DanceUserAggregate::class.java, eventStore) {

    @Autowired override fun setEventBus(eventBus: EventBus) {
        super.setEventBus(eventBus)
    }
}
