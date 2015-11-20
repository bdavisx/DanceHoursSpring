package com.tartner.domain.password

import com.tartner.dancehours.web.config.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer

@Configuration
@Import(TestPersistenceConfiguration::class, AxonConfiguration::class, PasswordConfiguration::class,
    JPAConfiguration::class, JacksonConfiguration::class)
public open class StandardIntegrationTestConfiguration {
    @Bean public open fun propertyConfigInDev(): PropertySourcesPlaceholderConfigurer {
        return PropertySourcesPlaceholderConfigurer()
    }
}
