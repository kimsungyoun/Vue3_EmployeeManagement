package com.example.backend.dto;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class EmployeeDTO {
    // CommonDTO
    private String id;

    // EmployeeDTO
    private String password;
    private String name;
    private String birth;
    private String phone;
    private String postal;
    private String address;
    private String detail;
    private String extra;
    private String dept;
    private String rule;
    private Date hiredate;

    // LeaveManagementDTO
    private Integer total;
    private Integer uses;

    // AttendanceDTO
    private String status;
    private Time on;
    private Time off;
    private java.sql.Date day;
    private Time work;
}
