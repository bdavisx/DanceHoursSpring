package com.tartner.domain.password;

import com.tartner.dancehours.querymodel.database.tables.records.AggregatePasswordsRecord;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import static com.tartner.dancehours.querymodel.database.tables.AggregatePasswords.AGGREGATE_PASSWORDS;

public class PasswordProjector {
    @Autowired private DSLContext dslContext;

    public PasswordProjector() {}

    public PasswordProjector( final DSLContext dslContext ) {
        this.dslContext = dslContext;
    }

    @EventHandler
    public void handle( PasswordSetEvent event ) {
        AggregatePasswordsRecord passwordRecord = dslContext
            .selectFrom( AGGREGATE_PASSWORDS )
            .where( AGGREGATE_PASSWORDS.AGGREGATE_ID
                .equal( event.getAggregateId() ) ).fetchOne();

        if (passwordRecord == null ) {
            passwordRecord = dslContext.newRecord( AGGREGATE_PASSWORDS )
                .setAggregateId( event.getAggregateId() );
        }

        passwordRecord
            .setPasswordHash( event.getPasswordHash() )
            .setSalt( event.getSalt() );
        passwordRecord.store();
    }
}
