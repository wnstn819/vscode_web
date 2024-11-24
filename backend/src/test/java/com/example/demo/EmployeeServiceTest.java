package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testCreateEmployee() {
        Employee employee = Employee.builder()
            .name("john")
            .joinDate(new Date())
            .password("1234")
            .rank("rank")
            .part("part")
            .build();
        boolean savedEmployee = employeeService.joinEmployee(employee);

        assertNotNull(savedEmployee);
        assertEquals(true, savedEmployee);
    }
}