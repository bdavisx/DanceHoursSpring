package com.tartner.dancehours.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories( "com.tartner" )
public class JPAConfiguration {
    @Autowired private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean =
            new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource( dataSource );
        factoryBean.setPersistenceUnitName( "persistenceUnit" );
        factoryBean.setPackagesToScan( "com.tartner" );
        factoryBean.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );

        return factoryBean;
    }

    @Bean
    public JpaTransactionManager jpaTransactionManager() {
        final JpaTransactionManager jpaTransactionManager =
            new JpaTransactionManager( entityManagerFactory().getObject() );
        jpaTransactionManager.setDataSource( dataSource );
        jpaTransactionManager.setEntityManagerFactory(
            entityManagerFactory().getObject() );
        return jpaTransactionManager;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        final LocalSessionFactoryBean localSessionFactoryBean =
            new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource( dataSource );
        return localSessionFactoryBean;
    }


//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        final HibernateTransactionManager transactionManager =
//            new HibernateTransactionManager();
//        transactionManager.setDataSource( dataSource );
//        transactionManager.setSessionFactory( sessionFactory().getObject() );
//        return transactionManager;
//    }
}
