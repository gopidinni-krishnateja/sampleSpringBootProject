package com.practice.springbootapp.csvUtils;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVReader;
import com.practice.springbootapp.modal.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
    public static  List read(MultipartFile file) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(file.getOriginalFilename()), ',');
        List<Employee> emps = new ArrayList<Employee>();

        // read line by line
        String[] record = null;

        while ((record = reader.readNext()) != null) {
            Employee emp = new Employee();
            emp.setEmpId(record[0]);
            emp.setName(record[1]);
            emps.add(emp);
        }
        reader.close();
        return  emps;

    }
}
