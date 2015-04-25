package com.tartner.dancehours.web.config;

import com.tartner.databasesupport.ExceptionTranslator;
import org.apache.commons.dbcp2.BasicDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = { "classpath:testPersistence.properties" })
public class TestPersistenceConfiguration {

    @Value("${dataSource.driverClassName}")
    private String driverClassName;
    @Value("${dataSource.datasourceURL}")
    private String datasourceURL;
    @Value("${dataSource.username}")
    private String username;
    @Value("${dataSource.password}")
    private String password;

    @Value("${jooq.sql.dialect}")
    private String jooqSQLDialect;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl( datasourceURL );
        dataSource.setUsername( username );
        dataSource.setPassword( password );
        dataSource.setDriverClassName( driverClassName );
        return dataSource;
    }

    @Bean
    public org.jooq.Configuration jooqConfiguration() {
        DefaultConfiguration configuration = new DefaultConfiguration();
        SQLDialect dialect = SQLDialect.valueOf( jooqSQLDialect );
        configuration.setSQLDialect( dialect );
        configuration.setConnectionProvider( connectionProvider() );
        configuration.setExecuteListenerProvider(
            new DefaultExecuteListenerProvider[]{
                new DefaultExecuteListenerProvider( exceptionTranslator() )
            } );
        return configuration;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        final DataSourceTransactionManager transactionManager =
            new DataSourceTransactionManager();
        transactionManager.setDataSource( dataSource() );
        return transactionManager;
    }

    @Bean
    public TransactionAwareDataSourceProxy transactionAwareDataSource() {
        return new TransactionAwareDataSourceProxy( dataSource() );
    }

    @Bean
    public DataSourceConnectionProvider connectionProvider() {
        return new DataSourceConnectionProvider(
            transactionAwareDataSource().getTargetDataSource() );
    }

    @Bean
    public DSLContext dsl() {
        return new DefaultDSLContext( jooqConfiguration() );
    }

    @Bean
    public ExceptionTranslator exceptionTranslator() {
        return new ExceptionTranslator();
    }
}

