package com.example.backend.dto;

import lombok.*;

import java.sql.Time;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    // CommonDTO
    private String empid;

    // EmployeeDTO
    private String password;
    private String empname;
    private String empbirth;
    private String empphone;
    private String emppostal;
    private String empaddr;
    private String empdetail;
    private String extra;
    private String empdept;
    private String emprule;
    private Date emphiredate;
    private String position;
    private Integer delstatus;
}
