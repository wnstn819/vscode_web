package com.example.demo.respository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByNameAndPassword(String name, String password);
    Employee findByName(String name);
}
