package com.tartner.dancehours.web.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import java.security.NoSuchAlgorithmException
import java.security.SecureRandom

@Configuration
public open class PasswordConfiguration {
    @Bean
    @throws(NoSuchAlgorithmException::class)
    public open fun secureRandom(): SecureRandom {
        return SecureRandom.getInstance("SHA1PRNG")
    }
}
