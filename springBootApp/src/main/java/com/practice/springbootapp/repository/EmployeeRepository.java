package com.practice.springbootapp.repository;

import com.practice.springbootapp.modal.Employee;

import java.util.List;


public interface EmployeeRepository  {
    public Employee findById(String id);
    List<Employee> getAllEmployee();
    public Employee saveEmployee(Employee employee);
}
