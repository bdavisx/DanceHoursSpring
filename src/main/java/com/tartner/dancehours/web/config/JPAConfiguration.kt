package com.tartner.dancehours.web.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.hibernate4.LocalSessionFactoryBean
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter

import javax.sql.DataSource

@Configuration
@EnableJpaRepositories("com.tartner")
public open class JPAConfiguration {
    @Autowired private var dataSource: DataSource? = null

    @Bean
    public open fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val factoryBean = LocalContainerEntityManagerFactoryBean()
        factoryBean.dataSource = dataSource
        factoryBean.persistenceUnitName = "persistenceUnit"
        factoryBean.setPackagesToScan("com.tartner")
        factoryBean.jpaVendorAdapter = HibernateJpaVendorAdapter()

        return factoryBean
    }

    @Bean
    public open fun jpaTransactionManager(): JpaTransactionManager {
        val jpaTransactionManager = JpaTransactionManager(entityManagerFactory().`object`)
        jpaTransactionManager.dataSource = dataSource
        jpaTransactionManager.entityManagerFactory = entityManagerFactory().getObject()
        return jpaTransactionManager
    }

    @Bean
    public open fun sessionFactory(): LocalSessionFactoryBean {
        val localSessionFactoryBean = LocalSessionFactoryBean()
        localSessionFactoryBean.setDataSource(dataSource)
        return localSessionFactoryBean
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
