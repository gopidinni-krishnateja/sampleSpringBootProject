package com.practice.springbootapp.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.DeleteResult;
import com.practice.springbootapp.csvUtils.CsvUtils;
import com.practice.springbootapp.modal.Employee;
import com.practice.springbootapp.repository.EmployeeRepository;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class EmployeeService  implements EmployeeRepository {
    private final Logger LOG = Logger.getLogger(EmployeeService.class);
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
    public String saveEmployee(Employee employee) {
        try {
             mongoTemplate.save(employee);
            LOG.info("Saved the Employee Record");
            return "Employeed Saved Success";
        } catch (Exception e){
            LOG.error("Failed to store employee record to db- "+e);
            return "Failed to store employee record";
        }

    }

    @Override
    public Employee updateEmployee(String id,Employee employee) {
        Query query = new Query();
        query.addCriteria(Criteria.where("empId").is(id));
        Employee employeeData = mongoTemplate.findOne(query,employee.getClass());
        employeeData.setName(employee.getName());
        return mongoTemplate.save(employeeData);
    }

    @Override
    public String deleterEmployee(String id) {
        try {
            DeleteResult isEmployeeRemoved;
            Query query = new Query();
            query.addCriteria(Criteria.where("empId").is(id));
            Employee employeeData = mongoTemplate.findOne(query,Employee.class);
            DeleteResult result= mongoTemplate.remove(employeeData);
            return "Successfully Deleted";
        }catch (Exception e){
            return "Id Not Found";
        }



    }

    @Override
    public String readEmployeeCSV(MultipartFile file) throws IOException {
        List<Employee> employees = CsvUtils.read(file);
        employees.forEach((employee -> {
           mongoTemplate.save(employee);
        }));
        return "Saved Successfully";
    }

    @Override
    public String generateEmployeeData() {
        ObjectMapper mapper = new ObjectMapper();
        List<Employee> employeeList = mongoTemplate.findAll(Employee.class);
        try {
            mapper.writeValue(new File("/home/semanticbits/Desktop/employeeData.json"), employeeList);
            LOG.info("Successfully generated employee json");
            return "Successfully generated employee json";
        } catch (JsonMappingException e) {
            LOG.error("Failed to generate Employee json file"+e);
            return "Failed to generate Employee json file";
        } catch (IOException e) {
            LOG.error( "Failed to generate Employee json file"+e);
            return "Failed to generate Employee json file";
        }

    }

}

