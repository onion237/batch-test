package com.example.demo.config;

import com.example.demo.util.SchemaAwareDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Autowired
    DataSourceProperties dataSourceProperties;
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource dataSource(){

        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
    @Bean
    public DataSource schemaAwareDataSource() {
        return new SchemaAwareDataSource(dataSource());
    }

    @Bean
    @Primary
    public PlatformTransactionManager defaultTransactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
    @Bean
    public PlatformTransactionManager batchTransactionManager(){
        return new DataSourceTransactionManager(schemaAwareDataSource());
    }
//    @Bean
//    @Primary
//    public PlatformTransactionManager springTransactionManager(){
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(schemaAwareDataSource());
//        return dataSourceTransactionManager;
//    }
}
