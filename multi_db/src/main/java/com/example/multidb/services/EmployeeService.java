package com.example.multidb.services;

import com.example.multidb.dao.EmployeeDao;
import com.example.multidb.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getEmployees() {
        return employeeDao.getEmployees();
    }

}
