package com.practice.springbootapp.service;

import com.practice.springbootapp.modal.Employee;
import com.practice.springbootapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeService  implements EmployeeRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Employee findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("empId").is(id));
        return mongoTemplate.findOne(query, Employee.class);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return mongoTemplate.findAll(Employee.class);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return mongoTemplate.save(employee);

    }
}

