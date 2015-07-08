package com.tartner.utilities

import org.axonframework.eventsourcing.EventSourcedAggregateRoot
import org.axonframework.test.FixtureConfiguration
import org.axonframework.test.Fixtures
import java.util.UUID

public object KFixtures {
    public fun <T : EventSourcedAggregateRoot<UUID>> newGivenWhenThenFixture(clazz: Class<T>):
        FixtureConfiguration<T> {
        return Fixtures.newGivenWhenThenFixture(clazz)
    }
}
