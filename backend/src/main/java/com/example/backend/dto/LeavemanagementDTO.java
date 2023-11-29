package com.example.backend.dto;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class LeavemanagementDTO {
    private String id;
    private Integer no;
    private Double total;
    private Double uses;

    EmployeeDTO employeeDTO;
}
