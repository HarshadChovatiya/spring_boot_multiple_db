package com.example.multidb.dao;

import com.example.multidb.model.Employee;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public EmployeeDao(@Qualifier("employeeSqlSessionTemplate") SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<Employee> getEmployees() {
        return sqlSessionTemplate.selectList("getEmployees");
    }
}
