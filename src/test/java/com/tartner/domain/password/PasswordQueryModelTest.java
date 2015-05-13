package com.tartner.domain.password;

import com.tartner.dancehours.querymodel.jpa.AggregatePasswordsEntity;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PasswordQueryModelTest {

    public static final String TestPassword = "P@ssw0rd";

    private static final int SaltLength = 32;

    private PasswordService passwordService;
    private PasswordQueryModel queryModel;
    private AggregatePasswordRepository repository;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        passwordService = new PasswordService();
        repository = mock( AggregatePasswordRepository.class );
        queryModel = new PasswordQueryModel( passwordService, repository );
    }

    @Test
    public void testPasswordsMatch() throws Exception {
        UUID id = UUID.randomUUID();

        TestPasswordHolder holder = TestPasswordHolder.CreateDefaultTest();

        AggregatePasswordsEntity password = new AggregatePasswordsEntity();
        password.setAggregateId( id );
        password.setPasswordHash( holder.getPasswordHash() );
        password.setSalt( holder.getSalt() );

        when( repository.findOne( id ) ).thenReturn( password );

        final boolean passwordsMatch =
            queryModel.passwordsMatch( id, TestPassword );
        assertThat( passwordsMatch, is( true ) );
    }
}
