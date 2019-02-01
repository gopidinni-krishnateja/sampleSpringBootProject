package com.practice.springbootapp.repository;

import com.practice.springbootapp.modal.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface EmployeeRepository  {
    public Employee findById(String id);
    List<Employee> getAllEmployee();
    public String saveEmployee(Employee employee);
    public Employee updateEmployee(String id,Employee employee);
    public String deleterEmployee(String id);
    public String readEmployeeCSV(MultipartFile file) throws IOException;
    public String generateEmployeeData();
}
