package com.tartner.dancehours.web.config;

import com.tartner.databasesupport.ExceptionTranslator;
import org.apache.commons.dbcp2.BasicDataSource;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:persistence.properties" })
public class PersistenceConfiguration {

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${dataSource.driverClassName}")
    private String driverClassName;
    @Value("${dataSource.datasourceURL}")
    private String datasourceURL;
    @Value("${dataSource.username}")
    private String username;
    @Value("${dataSource.password}")
    private String password;

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
    public Configuration jooqConfiguration() {
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.setSQLDialect( SQLDialect.POSTGRES );
        configuration.setConnectionProvider( connectionProvider() );
        configuration.setExecuteListenerProvider(
            new DefaultExecuteListenerProvider[]{
                new DefaultExecuteListenerProvider( exceptionTranslator() )
            } );
        return configuration;
    }

    @Bean
    public ExceptionTranslator exceptionTranslator() {
        return new ExceptionTranslator();
    }
}
