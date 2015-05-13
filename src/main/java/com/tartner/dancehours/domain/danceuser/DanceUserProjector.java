package com.tartner.dancehours.domain.danceuser;

// TODO: see Note:
// NOTE: I'm not sure that these should be in this package, they really pertain
// to the database...

import com.tartner.dancehours.domain.danceuser.external.DanceUserCreatedEvent;
import com.tartner.dancehours.querymodel.jpa.DanceUserEntity;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class DanceUserProjector {
    @PersistenceContext private EntityManager em;

    @EventHandler
    public void handle( DanceUserCreatedEvent event ) {
        DanceUserEntity userRecord = new DanceUserEntity();
        userRecord.setUserId( event.getUserId() );
        userRecord.setEmail( event.getEmail() );
        userRecord.setFirstName( event.getFirstName() );
        userRecord.setLastName( event.getLastName() );
        userRecord.setIsActive( true );
        em.persist( userRecord );
    }
}
