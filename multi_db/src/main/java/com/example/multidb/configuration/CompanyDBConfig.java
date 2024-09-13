package com.example.multidb.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
public class CompanyDBConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties companyDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "companyDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource companyDataSource() {
        return companyDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "companyHikariDataSource")
    @Primary
    public HikariDataSource companyHikariDataSource() {
        DataSourceProperties properties = companyDataSourceProperties();
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "companySqlSessionFactory")
    public SqlSessionFactory companySqlSessionFactory(
            @Qualifier("companyDataSource") final DataSource companyDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(companyDataSource);
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver()
                .getResource("classpath:mybatis-config.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "companySqlSessionTemplate")
    public SqlSessionTemplate companySqlSessionTemplate(
            @Qualifier("companySqlSessionFactory") final SqlSessionFactory companySqlSessionFactory) {
        return new SqlSessionTemplate(companySqlSessionFactory);
    }


}
