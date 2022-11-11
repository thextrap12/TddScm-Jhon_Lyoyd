package com.example.FizzBuzzTDD.Service;


import com.example.FizzBuzzTDD.model.Employee;
import com.example.FizzBuzzTDD.repository.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class EmployeeService {

    @Autowired
    private EmployeeRespository employeeRespository;

    public List<Employee> getAllEmployeesThatAreEarningMoreThan(Double amount) throws Exception {
        return employeeRespository
                .getAllEmployees()
                .stream()
                .filter(employee -> employee.getSalary() > amount)
                .collect(Collectors.toList());
    }
    public Optional<Employee> getEmployeeWithHighestSalary() throws Exception {
        return employeeRespository.getAllEmployees()
                .stream()
                .max(Comparator.comparing(Employee::getSalary));
    }
    public List<Employee> sortEmployeeAsc() throws Exception{
        return employeeRespository.getAllEmployees()
                .stream()
                .sorted(Comparator.comparing(Employee::getName))
                .toList();
    }
    public List<Employee> getAllEmployee() throws Exception{
        return employeeRespository.getAllEmployees()
                .stream()
                .toList();
    }
}
