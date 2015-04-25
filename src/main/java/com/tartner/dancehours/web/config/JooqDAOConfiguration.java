package com.tartner.dancehours.web.config;

import com.tartner.dancehours.querymodel.database.tables.daos.AggregatePasswordsDao;
import com.tartner.dancehours.querymodel.database.tables.daos.DanceUserDao;
import com.tartner.dancehours.querymodel.database.tables.daos.DanceUserRolesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqDAOConfiguration {
    @Autowired public org.jooq.Configuration configuration;

    @Bean
    public AggregatePasswordsDao aggregatePasswordsDao() {
        return new AggregatePasswordsDao( configuration );
    }

    @Bean
    public DanceUserDao danceUserDao() {
        return new DanceUserDao( configuration );
    }

    @Bean
    public DanceUserRolesDao danceUserRolesDao() {
        return new DanceUserRolesDao( configuration );
    }
}
