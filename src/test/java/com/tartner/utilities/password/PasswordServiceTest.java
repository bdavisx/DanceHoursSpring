package com.tartner.utilities.password;

import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PasswordServiceTest {

    public static final String TestPassword = "P@ssw0rd";

    private PasswordService service;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        service = new PasswordService( SecureRandom.getInstance( "SHA1PRNG" ) );
    }

    @Test
    public void createEncodedPassword() throws Exception {
        final EncodedPassword encodedPassword =
            service.createEncodedPassword( TestPassword );

        assertThat( encodedPassword, notNullValue() );
        assertThat( encodedPassword.hashCode(), notNullValue() );
    }

    @Test
    public void testPasswordsMatch() throws Exception {
        final EncodedPassword encodedPassword =
            service.createEncodedPassword( TestPassword );

        final boolean passwordsMatch =
            service.passwordsMatch( TestPassword, encodedPassword );

        assertThat( passwordsMatch, is( true ) );
    }
}
