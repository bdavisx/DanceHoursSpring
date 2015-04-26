package com.tartner.domain.password;

import com.tartner.dancehours.web.config.AxonConfiguration;
import com.tartner.dancehours.web.config.JooqDAOConfiguration;
import com.tartner.dancehours.web.config.PasswordConfiguration;
import com.tartner.dancehours.web.config.TestPersistenceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import( {
    TestPersistenceConfiguration.class,
    AxonConfiguration.class, PasswordConfiguration.class,
    JooqDAOConfiguration.class
    } )
public class StandardIntegrationTestConfiguration {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
