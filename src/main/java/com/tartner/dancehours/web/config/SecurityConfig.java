package com.tartner.dancehours.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // TODO: add a two-factor authentication option; if user has a known
    // email address (or phone) and they login from new location, email
    // or text link/code


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().permitAll();

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
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser( "user" ).password( "password" ).roles( "USER" );
    }

}
