package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="r_no")
    private Integer no;

    @Column(name = "emp_id")
    private String empid;

    @Column(name="r_day")
    private String day;

    @Column(name="r_content")
    private String content;

    @Column(name = "r_detail")
    private Date detail;

    @Column(name = "r_status")
    private Date status;
}
