package com.tartner.domain.password;

import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class PasswordServiceTest {

    public static final String TestPassword = "P@ssw0rd";

    private static final int SaltLength = 32;

    private PasswordEventFactory eventFactory;
    private PasswordService passwordService;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        passwordService = new PasswordService();
        eventFactory = new PasswordEventFactory( SecureRandom.getInstance( "SHA1PRNG" ),
            passwordService );
    }

    @Test
    public void createPasswordSetEvent() throws Exception {
        UUID id = UUID.randomUUID();

        final PasswordSetEvent encodedPassword =
            eventFactory.createPasswordSetEvent( id, TestPassword );

        assertThat( encodedPassword, notNullValue() );
        assertThat( encodedPassword.hashCode(), notNullValue() );
    }

}
