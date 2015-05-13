package com.tartner.dancehours.domain.danceuser.external;

import com.tartner.dancehours.querymodel.danceuser.DanceUserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DanceUserAggregateQueryModelTest {

    public static final String TestEmail = "bill@tartner.com";
    private DanceUserRepository dao;
    private DanceUserAggregateQueryModel queryModel;
    @Before
    public void setUp() throws Exception {

        dao = mock(DanceUserRepository.class);
        queryModel = new DanceUserAggregateQueryModel( dao );
    }

    @Test
    public void testEmailAlreadyExistsWhenExists() throws Exception {
        when( dao.existsByEmail( TestEmail ) ).thenReturn( true );

        assertThat( queryModel.emailAlreadyExists( TestEmail ), is( true ) );
    }

    @Test
    public void testEmailAlreadyExistsWhenDoesNotExist() throws Exception {
        when( dao.existsByEmail( TestEmail ) ).thenReturn( false );

        assertThat(queryModel.emailAlreadyExists( TestEmail ), is( false ) );
    }
}
