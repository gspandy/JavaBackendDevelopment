package com.lavor.springboot.sql.muti;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 多数据源配置
 * Created by lei.zeng on 2017/8/2.
 */
@Configuration
@EnableTransactionManagement
public class BeanConfig {
    /**
     * 获取application.properties中spring.datasource.one前缀配置的数据源
     * @return
     */
    @Bean(name = "oneDataSource")
    @Qualifier("oneDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.one")
    public DataSource oneDataSource() {
        return DataSourceBuilder.create().build();
    }
    /**
     * 获取application.properties中spring.datasource.two前缀配置的数据源
     * @return
     */
    @Bean(name = "twoDataSource")
    @Qualifier("twoDataSource")
    @ConfigurationProperties(prefix="spring.datasource.two")
    public DataSource twoDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 获取以oneDataSource为数据源的JdbcTemplate
     * @param dataSource
     * @return
     */
    @Bean(name = "oneJdbcTemplate")
    public JdbcTemplate oneJdbcTemplate(@Qualifier("oneDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 获取以oneDataSource为数据源的JdbcTemplate
     * @param dataSource
     * @return
     */
    @Bean(name = "twoJdbcTemplate")
    public JdbcTemplate twoJdbcTemplate(@Qualifier("twoDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}