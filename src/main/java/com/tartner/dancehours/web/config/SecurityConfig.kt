package com.tartner.dancehours.web.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
public open class SecurityConfig : WebSecurityConfigurerAdapter() {

    // TODO: add a two-factor authentication option; if user has a known
    // email address (or phone) and they login from new location, email
    // or text link/code


    @throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests().anyRequest().permitAll()

        //                .antMatchers("/resources/**").permitAll()
        //                .anyRequest().authenticated()
        //
        //            .and().formLogin()
        //                .loginPage("/login")
        //                .permitAll()
        //
        //            .and().logout()
        //                .permitAll();
    }

    @Autowired
    @throws(Exception::class)
    public open fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
    }

}
