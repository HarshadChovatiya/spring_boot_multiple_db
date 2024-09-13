package com.example.multidb.dao;

import com.example.multidb.model.Company;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public CompanyDao(@Qualifier("companySqlSessionTemplate") SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<Company> getCompanies() {
        return sqlSessionTemplate.selectList("getCompanies");
    }
}
