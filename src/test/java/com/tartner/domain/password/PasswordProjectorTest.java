package com.tartner.domain.password;

import com.tartner.IntegrationTestCategory;
import com.tartner.dancehours.querymodel.database.tables.daos.AggregatePasswordsDao;
import com.tartner.dancehours.querymodel.database.tables.pojos.AggregatePasswords;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=StandardIntegrationTestConfiguration.class)
@Transactional
@Category( IntegrationTestCategory.class )
@TransactionConfiguration(defaultRollback=true)
public class PasswordProjectorTest {
    @Autowired private AggregatePasswordsDao dao;
    @Autowired PasswordProjector projector;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
    }

    @Test
    public void checkEvent() throws Exception {
        TestPasswordHolder holder = TestPasswordHolder.CreateDefaultTest();

        final UUID uuid = UUID.randomUUID();

        final PasswordSetEvent event = new PasswordSetEvent( uuid,
            holder.getPasswordHash(), holder.getSalt() );
        projector.handle( event );

        final Optional<AggregatePasswords> first =
            dao.fetchByAggregateId( uuid ).stream().findFirst();

        assertThat( first.isPresent(), is( true ) );

        final AggregatePasswords password = first.get();
        assertThat( password.getPasswordHash(), is( holder.passwordHash ) );
        assertThat( password.getSalt(), is( holder.salt ) );
    }

}
