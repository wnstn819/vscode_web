package com.example.demo.respository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByNameAndPassword(String name, String password);
    Employee findByName(String name);

    @Query("SELECT e FROM Employee e ORDER BY e.joinDate DESC, e.part DESC")
    List<Employee> findAllSorted();
    
}
