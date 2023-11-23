package com.example.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class NoticeDTO {
    private int nno;
    private int empno;
    private String title;
    private String content;
    private int hit;
    private Date writedate;
}
