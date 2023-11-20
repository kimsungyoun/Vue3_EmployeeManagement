package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CurrentTimestamp;

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
    @CurrentTimestamp
    private Date day;

    @Column(name="r_content")
    private String content;

    @Column(name = "r_detail")
    private String detail;

    @Column(name = "r_status")
    private String status;
}
