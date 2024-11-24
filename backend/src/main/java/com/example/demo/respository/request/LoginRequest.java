package com.example.demo.respository.request;

import com.example.demo.model.UserRole;

public record LoginRequest(String name, String password, UserRole role){}
