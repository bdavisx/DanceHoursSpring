package com.tartner.domain.password;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=StandardIntegrationTestConfiguration.class)
@Transactional
public class PasswordProjectorTest {

    @Before
    public void setUp() throws NoSuchAlgorithmException {
    }

    @Test
    public void temp() throws Exception {

    }

}
