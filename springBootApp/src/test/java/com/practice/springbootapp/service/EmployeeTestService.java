package com.practice.springbootapp.service;
import com.practice.springbootapp.modal.Employee;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class EmployeeTestService {

    private @Mock MongoTemplate mongoTemplate;

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    BindingResult result;

    @Mock
    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
         mongoTemplate = Mockito.mock(MongoTemplate.class);

    }

    @Test
    public void findById(){

        Employee employee = new Employee();
        employee.setName("krishna");
        employee.setEmpId("1");
        Query query = new Query();
        query.addCriteria(Criteria.where("empId").is("1"));
        when(mongoTemplate.findOne(query,Employee.class)).thenReturn(employee);
        employeeService.findById("1");

    }

    @Test
    public void getAll(){
        Employee employee = new Employee();
        employee.setName("krishna");
        employee.setEmpId("1");
        List<Employee> employee1 = new ArrayList<Employee>();
        employee1.add(employee);
        when(mongoTemplate.findAll(Employee.class)).thenReturn(employee1);
        employeeService.getAllEmployee();
    }

    @Test
    public void saveEmployee(){
        Employee employee = new Employee();
        employee.setName("krishna");
        employee.setEmpId("1");
        when(mongoTemplate.save(employee)).thenReturn(employee);
        employeeService.saveEmployee(employee);

    }

    @Test
    public void updateEmployee(){
        Employee employee = new Employee();
        employee.setName("krishna");
        employee.setEmpId("1");
        Query query = new Query();
        query.addCriteria(Criteria.where("empId").is("1"));
        when(mongoTemplate.findOne(query,Employee.class)).thenReturn(employee);
        employeeService.updateEmployee("1",employee);

    }
}
