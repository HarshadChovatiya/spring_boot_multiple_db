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
public class EmployeeDBConfig {

    @Bean
    @ConfigurationProperties("spring.employee.db.datasource")
    public DataSourceProperties employeeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "employeeDataSource")
    @ConfigurationProperties(prefix = "spring.employee.db.datasource")
    public DataSource employeeDataSource() {
        return employeeDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "employeeHikariDataSource")
    public HikariDataSource employeeHikariDataSource() {
        DataSourceProperties properties = employeeDataSourceProperties();
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "employeeSqlSessionFactory")
    public SqlSessionFactory employeeSqlSessionFactory(
            @Qualifier("employeeDataSource") final DataSource employeeDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(employeeDataSource);
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver()
                .getResource("classpath:mybatis-config.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "employeeSqlSessionTemplate")
    public SqlSessionTemplate employeeSqlSessionTemplate(
            @Qualifier("employeeSqlSessionFactory") final SqlSessionFactory employeeSqlSessionFactory) {
        return new SqlSessionTemplate(employeeSqlSessionFactory);
    }


}
