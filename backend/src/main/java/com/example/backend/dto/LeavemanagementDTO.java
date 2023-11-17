package com.example.backend.dto;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class LeavemanagementDTO {
    // CommonDTO
    private String id;

    // LeaveManagementDTO
    private Integer no;
    private Integer total;
    private Integer uses;

    EmployeeDTO employeeDTO;
}
