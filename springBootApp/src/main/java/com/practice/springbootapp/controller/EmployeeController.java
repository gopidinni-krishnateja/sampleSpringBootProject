package com.practice.springbootapp.controller;


import com.practice.springbootapp.modal.Employee;
import com.practice.springbootapp.repository.EmployeeRepository;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class EmployeeController {

    private final Logger log = Logger.getLogger(EmployeeController.class);
    private final EmployeeRepository employee;

    public EmployeeController(EmployeeRepository employee) {
        this.employee = employee;
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Employee> getAllUsers() {
        return employee.getAllEmployee();
    }

    @RequestMapping(value="/createEmployee",method = RequestMethod.POST)
    public String createEmployee(@RequestBody Employee employee){
        log.warn("Controller called for creating employee");
        return this.employee.saveEmployee(employee);
    }

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    public Employee getById(@RequestParam("empId") String empId){
        return this.employee.findById(empId);
    }

    @RequestMapping(value="/updateEmployee", method = RequestMethod.PUT)
    public Employee updateEmployee(@RequestBody Employee employee, @RequestParam("empId")String empId){
        return this.employee.updateEmployee(empId,employee);
    }

    @RequestMapping(value = "/deleteEmployee",method = RequestMethod.GET)
    public String deleteEmployee(@RequestParam("empId")String id){
        return this.employee.deleterEmployee(id);
    }

    @RequestMapping(value = "/employeeList",method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String readEmployeeCSV( @RequestPart MultipartFile file) throws IOException {
        return this.employee.readEmployeeCSV(file);
    }

    @RequestMapping(value="/generateEmployeeData", method = RequestMethod.GET)
    public String generateJsonFile(){
        return this.employee.generateEmployeeData();
    }
}

