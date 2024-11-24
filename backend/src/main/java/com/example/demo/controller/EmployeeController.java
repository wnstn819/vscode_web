package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.model.UserRole;
import com.example.demo.respository.EmployeeRepository;
import com.example.demo.respository.request.LoginRequest;


@RestController
@RequestMapping("/api")
public class EmployeeController {
    

    @Autowired
    private EmployeeRepository employeeRepository; 

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        
        Employee employee = employeeRepository.findByNameAndPassword(loginRequest.name(),loginRequest.password());



        if(employee.getRole().equals(UserRole.ADMIN)){
            return ResponseEntity.ok("로그인 성공")    ;
        }else{
            return ResponseEntity.status(404).body("로그인 실패!");
        }
        
    }

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getList(){
        List<Employee> list = employeeRepository.findAll();

        return ResponseEntity.ok(list);
    }

}
