package com.tartner.dancehours.domain.danceuser;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DanceUserAggregateRepository
    extends EventSourcingRepository<DanceUserAggregate> {

    @Autowired
    public DanceUserAggregateRepository(
        final EventStore eventStore ) {
        super( DanceUserAggregate.class, eventStore );
    }
}
