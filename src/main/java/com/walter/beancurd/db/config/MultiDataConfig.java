package com.walter.beancurd.db.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author walter
 * @date 2021/11/18 上午12:12
 *
 * <a href="https://zhuanlan.zhihu.com/p/509877891">H2介绍</a>
 */
@Configuration
@Slf4j
public class MultiDataConfig {

    /**
     * db1 配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSourceProperties db1DataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource db1DataSource(@Qualifier("db1DataSourceProperties")DataSourceProperties dataSourceProperties){
        log.info("**db1 url**:{}",dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public PlatformTransactionManager db1TxManager(@Qualifier("db1DataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * db2配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSourceProperties db2DataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource db2DataSource(@Qualifier("db2DataSourceProperties")DataSourceProperties dataSourceProperties){
        log.info("**db2 url**:{}",dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public PlatformTransactionManager db2TxManager(@Qualifier("db2DataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
