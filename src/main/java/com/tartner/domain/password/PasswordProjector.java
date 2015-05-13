package com.tartner.domain.password;

import com.tartner.dancehours.querymodel.jpa.AggregatePasswordsEntity;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordProjector {
    @Autowired private AggregatePasswordRepository repository;

    public PasswordProjector() {}

    public PasswordProjector( final AggregatePasswordRepository repository ) {
        this.repository = repository;
    }

    @EventHandler
    public void handle( PasswordSetEvent event ) {
        AggregatePasswordsEntity passwordRecord =
            repository.findOne( event.getAggregateId() );

        if (passwordRecord == null ) {
            passwordRecord = new AggregatePasswordsEntity();
            passwordRecord.setAggregateId( event.getAggregateId() );
        }

        passwordRecord.setPasswordHash( event.getPasswordHash() );
        passwordRecord.setSalt( event.getSalt() );

        repository.save( passwordRecord );
    }
}
