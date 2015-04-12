package com.tartner.dancehours.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.tartner.dancehours.config" })
@PropertySource(value = { "classpath:persistence.properties" })
public class HibernateConfiguration {

    @Value("${dataSource.driverClassName}")
    private String driverClassName;
    @Value("${dataSource.datasourceURL}")
    private String datasourceURL;
    @Value("${dataSource.username}")
    private String username;
    @Value("${dataSource.password}")
    private String password;
    @Value("${hibernate.hibernateDialect}")
    private String hibernateDialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;
    @Value("${hibernate.show_sql}")
    private boolean showSQL;
    @Value("${hibernate.format_sql}")
    private boolean formatSQL;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.tartner.dancehours.domain" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put( "hibernate.dialect", hibernateDialect );
        properties.put( "hibernate.show_sql", showSQL );
        properties.put( "hibernate.format_sql", formatSQL );
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName( driverClassName );
        dataSource.setUrl( datasourceURL );
        dataSource.setUsername( username );
        dataSource.setPassword( password );
        return dataSource;
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean configureEntityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
//            new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource( dataSource() );
//        entityManagerFactoryBean.setPackagesToScan( "com.tartner" );
//        entityManagerFactoryBean.setJpaVendorAdapter(
//            new HibernateJpaVendorAdapter() );
//
//        Properties jpaProperties = new Properties();
//        jpaProperties.put( org.hibernate.cfg.Environment.DIALECT,
//            hibernateDialect );
//        jpaProperties.put( org.hibernate.cfg.Environment.HBM2DDL_AUTO,
//            hbm2ddlAuto );
//        entityManagerFactoryBean.setJpaProperties( jpaProperties );
//
//        return entityManagerFactoryBean;
//    }

    @Bean
    public HibernateTemplate hibernateTemplate() {
        return new HibernateTemplate(sessionFactory().getObject());
    }

    @Bean @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory( sessionFactory );
        return txManager;
    }
}
