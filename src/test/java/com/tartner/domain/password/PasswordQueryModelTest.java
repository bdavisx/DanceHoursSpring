package com.tartner.domain.password;

import com.tartner.dancehours.querymodel.database.tables.daos.AggregatePasswordsDao;
import com.tartner.dancehours.querymodel.database.tables.pojos.AggregatePasswords;
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
    private AggregatePasswordsDao dao;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        passwordService = new PasswordService();
        dao = mock( AggregatePasswordsDao.class );
        queryModel = new PasswordQueryModel( passwordService, dao );
    }

    @Test
    public void testPasswordsMatch() throws Exception {
        UUID id = UUID.randomUUID();

        TestPasswordHolder holder = TestPasswordHolder.CreateDefaultTest();

        AggregatePasswords password = new AggregatePasswords( id,
            holder.getPasswordHash(), holder.getSalt() );
        when( dao.fetchOneByAggregateId( id ) ).thenReturn( password );

        final boolean passwordsMatch =
            queryModel.passwordsMatch( id, TestPassword );
        assertThat( passwordsMatch, is( true ) );
    }
}
