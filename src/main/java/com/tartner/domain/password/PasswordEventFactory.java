package com.tartner.domain.password;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.UUID;

public class PasswordEventFactory {
    /* Note: Note why the right margin is so small, and where it came from
        (Python style document) - also fits well on most website code windows. */

    private static final Logger log =
        LoggerFactory.getLogger( PasswordEventFactory.class );

    private static final int SaltLength = 32;

    private final SecureRandom random;
    private final PasswordService passwordService;

    public PasswordEventFactory( final SecureRandom random,
        final PasswordService passwordService ) {
        this.random = random;
        this.passwordService = passwordService;
    }

    public PasswordSetEvent createPasswordSetEvent( UUID aggregateId,
        String password ) {
        final byte[] salt = random.generateSeed( SaltLength );
        KeySpec keySpecification =
            passwordService.createKeySpecification( password, salt );
        final byte[] passwordHash =
            passwordService.createPasswordHash( keySpecification );

        // store the salt with the password
        return new PasswordSetEvent( aggregateId, passwordHash, salt );
    }

    private byte[] createPasswordHash( final KeySpec keySpecification ) {
        return passwordService.createPasswordHash( keySpecification );
    }

    private PBEKeySpec createKeySpecification( final String password,
        final byte[] salt ) {
        return passwordService.createKeySpecification( password, salt );
    }
}
