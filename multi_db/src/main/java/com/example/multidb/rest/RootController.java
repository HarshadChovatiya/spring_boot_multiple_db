package com.example.multidb.rest;

import com.example.multidb.services.CompanyService;
import com.example.multidb.services.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RootController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;

    public RootController(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/company-employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getCompanyEmployee() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("companies", companyService.getCompanies());
        responseMap.put("employees", employeeService.getEmployees());
        return responseMap;
    }

}
