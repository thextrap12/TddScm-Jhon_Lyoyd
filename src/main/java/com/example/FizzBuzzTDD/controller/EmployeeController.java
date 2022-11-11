package com.example.FizzBuzzTDD.controller;

import com.example.FizzBuzzTDD.Service.EmployeeService;
import com.example.FizzBuzzTDD.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Comparator;
import java.util.List;

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    public List<Employee> getEmployeeThenReturnValue() throws Exception {
        return employeeService.getAllEmployee();
    }


}
