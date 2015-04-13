package com.tartner.dancehours.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@Configuration
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
        try {
            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setUrl( datasourceURL );
            dataSource.setUser( username );
            dataSource.setPassword( password );
            return dataSource;
        } catch( SQLException ex ) {
            throw new IllegalArgumentException(
                ("Can't find database url " + datasourceURL), ex );
        }
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        final DataSourceTransactionManager transactionManager =
            new DataSourceTransactionManager();
        transactionManager.setDataSource( dataSource() );
        return transactionManager;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource( dataSource() );
        factory.setConfigLocation( new FileSystemResource( "D:\\source\\DanceHoursSpring\\src\\main\\resources\\SQLMapConfig.xml" ) );
//        factory.setMapperLocations( new Resource[] {
//            new FileSystemResource( "D:\\source\\DanceHoursSpring\\src\\main\\java\\com\\tartner\\dancehours\\database\\DanceUser.xml" ) } );
//        factory.setMapperLocations( ResourcePatternUtils
//            .getResourcePatternResolver( resourceLoader ).getResources(
//            "classpath*:com/tartner/dancehours/database/*.xml" ) );
        return factory;
    }
}
