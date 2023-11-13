package com.example.backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RequestDTO {
    private int r_no;
    private int no;
    private String reason;
    private String result;
    private Date day; // workday
}
