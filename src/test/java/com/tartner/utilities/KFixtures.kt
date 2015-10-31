package com.tartner.utilities

import com.tartner.dancehours.DanceHoursId
import org.axonframework.eventsourcing.EventSourcedAggregateRoot
import org.axonframework.test.FixtureConfiguration
import org.axonframework.test.Fixtures

public object KFixtures {
    public fun <T : EventSourcedAggregateRoot<DanceHoursId>> newGivenWhenThenFixture(clazz: Class<T>):
        FixtureConfiguration<T> {
        return Fixtures.newGivenWhenThenFixture(clazz)
    }
}
