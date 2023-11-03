package com.example.backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDTO {
    private String id;
    private String name;
    private String phone;
    private String birth;
    private String postal;
    private String address;
    private String detail;
    private String dept;
    private String rule;
}
