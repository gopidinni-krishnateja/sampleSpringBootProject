package com.practice.springbootapp.controller;


import com.practice.springbootapp.modal.Employee;
import com.practice.springbootapp.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        System.out.println("&&&&&&&&&&&&&&");
        return this.employee.readEmployeeCSV(file);
    }
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public void uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException {
         this.employee.readEmployeeCSV(file);
    }
}

