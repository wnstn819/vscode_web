package com.example.demo.respository.request;

import com.example.demo.model.Employee;

public record UpdateEmployeeRequest(Long id, Employee updates){}
