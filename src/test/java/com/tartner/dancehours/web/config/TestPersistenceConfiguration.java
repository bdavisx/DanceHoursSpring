package com.tartner.dancehours.web.config;

import com.tartner.databasesupport.ExceptionTranslator;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
@PropertySource(value = { "/persistence.properties" })
public class TestPersistenceConfiguration {

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

    @Value("${jooq.sql.dialect}")
    private String jooqSQLDialect;

    @Bean
    public DataSource dataSource() {
        try {
            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setUrl( datasourceURL );
            dataSource.setUser( username );
            dataSource.setPassword( password );
            return dataSource;
        } catch( SQLException e ) {
            throw new RuntimeException( e );
        }
    }

    @Bean
    public org.jooq.Configuration jooqConfiguration() {
        DefaultConfiguration configuration = new DefaultConfiguration();
        SQLDialect dialect = SQLDialect.POSTGRES;
        configuration.setSQLDialect( dialect );
        configuration.setConnectionProvider( connectionProvider() );
        configuration.setExecuteListenerProvider(
            new DefaultExecuteListenerProvider[]{
                new DefaultExecuteListenerProvider( exceptionTranslator() )
            } );
        return configuration;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
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

