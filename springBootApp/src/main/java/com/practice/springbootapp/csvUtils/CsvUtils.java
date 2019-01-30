package com.practice.springbootapp.csvUtils;


import com.practice.springbootapp.modal.Employee;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
    public static List<Employee> read(MultipartFile file) throws IOException {
        List<Employee> emps = new ArrayList<Employee>();
        ICsvBeanReader beanReader = new CsvBeanReader(new InputStreamReader(file.getInputStream()),
                CsvPreference.STANDARD_PREFERENCE);
        final String[] nameMapping = new String[] { "empId", "name" };

        final String[] header = beanReader.getHeader(true);
        final CellProcessor[] processors = getProcessors();
        Employee emp;
        while ((emp = beanReader.read(Employee.class, nameMapping, processors)) != null) {
            emps.add(emp);
        }
        beanReader.close();
        return emps;
    }

    private static CellProcessor[] getProcessors() {
        final CellProcessor[] processors = new CellProcessor[] {
                new UniqueHashCode(), // ID
                new NotNull(), // Name
        };
        return processors;
    }
}
