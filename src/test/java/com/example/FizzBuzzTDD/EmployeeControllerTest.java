package com.example.FizzBuzzTDD;

import com.example.FizzBuzzTDD.Service.EmployeeService;
import com.example.FizzBuzzTDD.controller.EmployeeController;
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

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {



    Employee jojo = new Employee("Jojo", 20000d);
    Employee jerpy = new Employee("Jerpy", 150000d);
    Employee jecheal = new Employee("Jecheal", 100000d);
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setup() throws Exception {
        List<Employee> employees = List.of(jojo
                , jerpy, jecheal);
        Mockito.when(employeeService.getAllEmployee())
                .thenReturn(employees);
    }
    @Test
    @DisplayName("Given a request, " +
            "When employeeController is executed " +
            "Return the value of getAllEmployee")
    public void getEmployeeThenReturnValue() throws Exception {
        //arrange

        List<Employee> filteredEmployees = employeeController.getEmployeeThenReturnValue();

        //Assert
        assertThat(filteredEmployees).contains(jojo, jecheal, jerpy);
    }
}
