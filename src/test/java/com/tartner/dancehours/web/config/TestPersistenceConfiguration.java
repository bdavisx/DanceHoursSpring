package com.tartner.dancehours.web.config;

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
    public PlatformTransactionManager platformTransactionManager() {
        final DataSourceTransactionManager transactionManager =
            new DataSourceTransactionManager();
        transactionManager.setDataSource( dataSource() );
        return transactionManager;
    }

    @Bean
    public TransactionAwareDataSourceProxy transactionAwareDataSource() {
        return new TransactionAwareDataSourceProxy( dataSource() );
    }
}

