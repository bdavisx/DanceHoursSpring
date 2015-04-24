package com.tartner.domain.password;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;

public class EncodedPassword
    extends AbstractAnnotatedEntity {

    // TODO: need to create passwordHash and Salt classes, make sure
    // those and this one are compatible w/ XStream; then change the
    // event to have an EncodedPassword instead of the separate properties
    private final byte[] passwordHash;
    private final byte[] salt;

    public EncodedPassword( PasswordSetEvent event ) {
        passwordHash = event.getPasswordHash();
        salt = event.getSalt();
    }

}
