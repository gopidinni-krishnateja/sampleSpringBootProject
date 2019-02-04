package com.practice.springbootapp.controller;

import com.practice.springbootapp.modal.Employee;
import com.practice.springbootapp.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;
import static org.mockito.Mockito.when;

public class EmployeeTestController {
    @InjectMocks
    EmployeeController employeeController;

    @Mock
    BindingResult result;



    @Mock
    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    EmployeeService employeeService;
    @Test
    public void addEmployee() {
        Employee employee =  new Employee();
        employee.setEmpId("1");
        employee.setName("krishna");
        System.out.println(employee);
        employeeController.createEmployee(employee);
        when(employeeService.saveEmployee(employee)).thenReturn("Saved Successfully");

    }

    @Test
    public void getAllEmployees() {
        List<Employee> actualemployeeList = employeeController.getAllUsers();
        when(employeeService.getAllEmployee()).thenReturn(actualemployeeList);

    }

    @Test
    public void getById(){
        Employee employee = employeeController.getById("1");
        when(employeeService.findById("1")).thenReturn(employee);
    }

    @Test
    public void updateEmployee(){
        Employee employee =  new Employee();
        Employee updatedEmployee = employeeController.updateEmployee(employee,"1");
        when(employeeService.updateEmployee("1",employee)).thenReturn(employee);
    }

    @Test
    public void readEmployee(){
        Employee employee =  new Employee();
        employeeController.deleteEmployee("1");
        when(employeeService.deleterEmployee("1")).thenReturn("Successfully Deleted");
    }

    @Autowired
    private WebApplicationContext webApplicationContext;


    public static Employee createEmployee(String id,String name){
        Employee employee = new Employee();
        employee.setEmpId(id);
        employee.setName(name);
        return employee;
    }
}
