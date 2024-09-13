package com.example.multidb.services;

import com.example.multidb.dao.CompanyDao;
import com.example.multidb.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public List<Company> getCompanies() {
        return companyDao.getCompanies();
    }

}
