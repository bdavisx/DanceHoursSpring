package com.tartner.dancehours.domain.danceuser;

// TODO: see Note:
// NOTE: I'm not sure that these should be in this package, they really pertain
// to the database...

import com.tartner.dancehours.domain.danceuser.external.DanceUserCreatedEvent;
import com.tartner.dancehours.querymodel.database.tables.records.DanceUserRecord;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.tartner.dancehours.querymodel.database.tables.DanceUser.DANCE_USER;

@Component
public class DanceUserProjector {
    @Autowired
    private DSLContext dslContext;

    @EventHandler
    public void handle( DanceUserCreatedEvent event ) {
        DanceUserRecord userRecord = dslContext
            .newRecord( DANCE_USER )
                .setUserId( event.getUserId() )
                .setEmail( event.getEmail() )
                .setFirstName( event.getFirstName() )
                .setLastName( event.getLastName() )
                .setIsActive( true );
        userRecord.store();
    }
}
