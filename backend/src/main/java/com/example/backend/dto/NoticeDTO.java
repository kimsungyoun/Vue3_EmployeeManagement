package com.example.backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeDTO {
    private int nno;
    private String empid;
    private String title;
    private String content;
    private int hit;
    private Date writedate;
}
