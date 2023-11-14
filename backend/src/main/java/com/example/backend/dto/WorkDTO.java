package com.example.backend.dto;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class WorkDTO {
    // CommonDTO
    private String id;

    // Work DTO
    private Integer no;
    private String status;
    private Time on;
    private Time off;
    private Date day;
    private Time work;
}
