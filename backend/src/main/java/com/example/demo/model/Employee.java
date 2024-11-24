package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    private String name;

    private String password;
    
    private String part;

    private String rank;

    private LocalDate joinDate;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return password;
    }

    public String getPart(){
        return part;
    }

    public void setPart(String part){
        this.part = part;
    }

    public String getRank(){
        return rank;
    }

    public void setRank(String rank){
        this.rank = rank;
    }
    public LocalDate getJoinDate(){
        return joinDate;
    }

    public void setJoinDate(LocalDate date){
        this.joinDate = date;
    }

    public UserRole getRole(){
        return role;
    }
    
}
