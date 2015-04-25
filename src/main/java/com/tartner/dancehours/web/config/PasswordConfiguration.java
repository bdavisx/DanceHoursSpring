package com.tartner.dancehours.web.config;

import com.tartner.domain.password.PasswordEventFactory;
import com.tartner.domain.password.PasswordProjector;
import com.tartner.domain.password.PasswordQueryModel;
import com.tartner.domain.password.PasswordService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Configuration
public class PasswordConfiguration {
    @Bean
    public SecureRandom secureRandom() throws NoSuchAlgorithmException {
        return SecureRandom.getInstance( "SHA1PRNG" );
    }

    @Bean
    public PasswordService passwordService() {
        return new PasswordService();
    }

    @Bean
    public PasswordEventFactory passwordEventFactory() {
        return new PasswordEventFactory();
    }

    @Bean
    public PasswordProjector passwordProjector() {
        return new PasswordProjector();
    }

    @Bean
    public PasswordQueryModel passwordQueryModel() {
        return new PasswordQueryModel();
    }
}
