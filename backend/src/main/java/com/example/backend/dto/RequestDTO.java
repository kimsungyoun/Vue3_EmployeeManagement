package com.example.backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RequestDTO {
    private int no;
    private String id;
    private String content;
    private String detail;
    private String status;
    private Date day;
}
