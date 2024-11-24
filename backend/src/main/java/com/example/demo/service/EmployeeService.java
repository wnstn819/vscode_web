package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.respository.EmployeeRepository;
import com.example.demo.respository.request.UpdateEmployeeRequest;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public List<Employee> getEmployeeList(){
        List<Employee> list = employeeRepository.findAll();
        List<Employee> sortList = employeeRepository.findAllSorted();

        for(Employee em : sortList){
            System.out.println(em.getName());
        }
        return list;
    }

    @Transactional
    public boolean joinEmployee(Employee data){
        Employee employee = employeeRepository.findByName(data.getName());

        if(employee == null){
          
            Employee em = Employee.builder()
            .name(data.getName())
            .joinDate(new Date())
            .password(data.getPassword())
            .rank(data.getRank())
            .part(data.getPart())
            .build();

            employeeRepository.save(em);
            
        }else{
            return false;
        }
        return true;
    }

    @Transactional
    public boolean patchEmployee(Long id, UpdateEmployeeRequest updateEmployeeRequest){
        Employee employee = employeeRepository.findById(id).orElse(null);

        if(employee != null){
            if(updateEmployeeRequest.updates().getName() != null){
                employee.setName(updateEmployeeRequest.updates().getName());
            }

            if(updateEmployeeRequest.updates().getPart() != null){
                employee.setPart(updateEmployeeRequest.updates().getPart());
            }

            if(updateEmployeeRequest.updates().getPassword() != null){
                employee.setPassword(updateEmployeeRequest.updates().getPassword());
            }


        }else{
            return false;
        }

        employeeRepository.save(employee);
        return true;
        
        
    }

    @Transactional
    public boolean deleteUserById(Long id) {
        if (employeeRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("User with ID " + id + " does not exist.");
        }
        employeeRepository.deleteById(id);
        return true;
    }
}
