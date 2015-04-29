package com.tartner.dancehours.domain.danceuser.external;

import com.tartner.dancehours.querymodel.database.tables.daos.DanceUserDao;
import com.tartner.dancehours.querymodel.database.tables.pojos.DanceUser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DanceUserAggregateQueryModelTest {

    public static final String TestEmail = "bill@tartner.com";
    private DanceUserDao dao;
    private DanceUserAggregateQueryModel queryModel;
    @Before
    public void setUp() throws Exception {

        dao = mock(DanceUserDao.class);
        queryModel = new DanceUserAggregateQueryModel( dao );
    }

    @Test
    public void testEmailAlreadyExistsWhenExists() throws Exception {
        when( dao.fetchByEmail( TestEmail ) ).thenReturn(
            Arrays.asList( new DanceUser[]{ new DanceUser() } ) );

        assertThat( queryModel.emailAlreadyExists( TestEmail ), is( true ) );
    }

    @Test
    public void testEmailAlreadyExistsWhenDoesNotExist() throws Exception {
        when( dao.fetchByEmail( TestEmail ) ).thenReturn(
            new ArrayList<DanceUser>() );

        assertThat(queryModel.emailAlreadyExists( TestEmail ), is( false ) );
    }
}
