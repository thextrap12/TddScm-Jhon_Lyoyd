package com.example.FizzBuzzTDD;

import com.example.FizzBuzzTDD.Service.EmployeeService;
import com.example.FizzBuzzTDD.model.Employee;
import com.example.FizzBuzzTDD.repository.EmployeeRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    Employee jojo = new Employee("Jojo", 20000d);
    Employee jerpy = new Employee("Jerpy", 150000d);
    Employee jecheal = new Employee("Jecheal", 100000d);
    @Mock
    private EmployeeRespository employeeRespository;
    @InjectMocks
    private EmployeeService employeeService = new EmployeeService();

    @BeforeEach
    public void setup() throws Exception {
        List<Employee> employees = List.of(jojo
                , jerpy, jecheal);
        Mockito.when(employeeRespository.getAllEmployees())
                .thenReturn(employees);
    }

    @Test
    @DisplayName("Given employees from repository with the setup above " +
            "WHEN getAllEmployeesThatAreEarningMoreThan is executed with input 90000 " +
            "THEN result should return Jerpy and jecheal")
    public void testEmployeesEarningMoreThanAmount() throws Exception {
        //arrange

        //act
        List<Employee> filteredEmployees = employeeService.getAllEmployeesThatAreEarningMoreThan(90000d);

        //assert
        assertThat(filteredEmployees).contains(jerpy, jecheal);

    }

    @Test
    @DisplayName("Given employees from repository with the setup above" +
            "WHEN sortEmployeeAsc is executed" +
            "THEN result should return the ASC order of the Data")

    public void sortByName() throws Exception {
        //Arrange

        //Act
        List<Employee> filteredEmployees = employeeService.sortEmployeeAsc();

        //Assert
        assertThat(filteredEmployees).contains(jojo, jecheal, jerpy);
    }

    @Test
    @DisplayName("Given employees from repository with the setup above " +
            "WHEN getAllEmployeesThatAreEarningMoreThan is executed with input 50000 " +
            "THEN result should return the value of ")

    public void EmployeesThatAreEarningMoreThan() throws Exception {
        //Arrange

        //Act
        Optional<Employee> filteredEmployees =
                employeeService.getEmployeeWithHighestSalary();
        //Assert
        assertThat(filteredEmployees).contains(jerpy);
    }
}