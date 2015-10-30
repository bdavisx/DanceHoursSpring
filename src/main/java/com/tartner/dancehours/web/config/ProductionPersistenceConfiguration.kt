package com.tartner.dancehours.web.config

import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import javax.sql.DataSource

@Configuration
@PropertySource(value = *arrayOf("classpath:persistence.properties"))
public open class ProductionPersistenceConfiguration {
    private @Autowired var environment: Environment? = null

    @Bean
    public open fun dataSource(): DataSource {
        val dataSource = BasicDataSource()
        // TODO: is there a better way to handle this? (datasourceURL())
        dataSource.url = datasourceURL()
        dataSource.username = username()
        dataSource.password = password()
        dataSource.driverClassName = driverClassName()
        return dataSource
    }

    @Bean
    public open fun transactionAwareDataSource(): TransactionAwareDataSourceProxy {
        return TransactionAwareDataSourceProxy(dataSource())
    }

    private fun driverClassName(): String? {
        return environment?.getProperty("dataSource.driverClassName")
    }

    private fun datasourceURL(): String? {
        return environment?.getProperty("dataSource.datasourceURL")
    }

    private fun username(): String? {
        return environment?.getProperty("dataSource.username")
    }

    private fun password(): String? {
        return environment?.getProperty("dataSource.password")
    }
}
