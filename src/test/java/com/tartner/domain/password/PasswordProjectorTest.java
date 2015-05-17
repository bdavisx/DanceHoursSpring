package com.tartner.domain.password;

import com.tartner.IntegrationTestCategory;
import com.tartner.dancehours.querymodel.jpa.AggregatePasswordsEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=StandardIntegrationTestConfiguration.class)
@ComponentScan(basePackages = { "com.tartner.domain" })
@Transactional
@TransactionConfiguration(defaultRollback=true)
@Category( IntegrationTestCategory.class )
public class PasswordProjectorTest {
    @Autowired private AggregatePasswordRepository repository;
    private PasswordProjector projector;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
    }

    @Test
    public void checkEvent() throws Exception {
        projector = new PasswordProjector( repository );

        TestPasswordHolder holder = TestPasswordHolder.CreateDefaultTest();

        final UUID uuid = UUID.randomUUID();

        final PasswordSetEvent event = new PasswordSetEvent( uuid,
            holder.getPasswordHash(), holder.getSalt() );
        projector.handle( event );

        final AggregatePasswordsEntity passwordsEntity = repository.findOne( uuid );

        assertThat( passwordsEntity, notNullValue() );

        assertThat( passwordsEntity.getPasswordHash(), is( holder.passwordHash ) );
        assertThat( passwordsEntity.getSalt(), is( holder.salt ) );
    }

}
