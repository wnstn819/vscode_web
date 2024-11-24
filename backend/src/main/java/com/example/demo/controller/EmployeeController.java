package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.model.UserRole;
import com.example.demo.respository.EmployeeRepository;
import com.example.demo.respository.request.LoginRequest;
import com.example.demo.respository.request.UpdateEmployeeRequest;
import com.example.demo.respository.response.ResponseDTO;
import com.example.demo.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeController {
    

    @Autowired
    private EmployeeRepository employeeRepository; 

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<String>> login(@RequestBody LoginRequest loginRequest){

        
        Employee employee = employeeRepository.findByNameAndPassword(loginRequest.name(),loginRequest.password());


        if(employee != null){
            if(employee.getRole().equals(UserRole.ADMIN)){
                return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK,"로그인 성공"))    ;
            }else{
                return ResponseEntity.ok(ResponseDTO.res(HttpStatus.UNAUTHORIZED,"관리자가 아닙니다."))    ;
            }
            
        }else{
            return ResponseEntity.ok(ResponseDTO.res(HttpStatus.BAD_REQUEST,"사용자가 없습니다."));
        }

        
        
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO<List<Employee>>> getList(){
        try{
            List<Employee> list = employeeService.getEmployeeList();
        return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK,"성공",list));
        }catch(IllegalArgumentException e){
            return ResponseEntity.ok(ResponseDTO.res(HttpStatus.NOT_FOUND,e.getMessage()));
        }
        
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseDTO<String>> joinEmployee(@RequestBody Employee data){

        boolean result = employeeService.joinEmployee(data);

       System.out.println(data.getName() + " : " + data.getRole());

        if(result){
            return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK,"생성 완료"));
        }else{
            return ResponseEntity.ok(ResponseDTO.res(HttpStatus.BAD_REQUEST,"이미 존재하는 이름입니다."));
        }
        
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ResponseDTO<String>> delEmployee(@PathVariable Long id){
        try {
            if(employeeService.deleteUserById(id)){
                return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK, "삭제 성공"));
            }else{
                return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK, "삭제 실패"));
            }
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(ResponseDTO.res(HttpStatus.NOT_FOUND, e.getMessage()));
        }
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<ResponseDTO<String>> patchEmployee(@PathVariable Long id,@RequestBody UpdateEmployeeRequest updateEmployeeRequest){

        boolean result = employeeService.patchEmployee(id, updateEmployeeRequest);
        if(result){
            return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK,"성공"));
        }else{
            return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK,"실패"));
        }
        
    }

}
