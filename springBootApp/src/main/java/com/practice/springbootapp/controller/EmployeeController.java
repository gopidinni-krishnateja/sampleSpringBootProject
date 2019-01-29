package com.practice.springbootapp.controller;


import com.practice.springbootapp.modal.Employee;
import com.practice.springbootapp.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class EmployeeController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final EmployeeRepository employee;

    public EmployeeController(EmployeeRepository employee) {
        this.employee = employee;
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Employee> getAllUsers() {
        LOG.info("Getting all users.");
        return employee.getAllEmployee();
    }

    @RequestMapping(value="/createEmployee",method = RequestMethod.POST)
    public  Employee createEmployee(@RequestBody Employee employee){
        return this.employee.saveEmployee(employee);
    }

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    public Employee getById(@RequestParam("empId") String empId){
        return this.employee.findById(empId);
    }
}

