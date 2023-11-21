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
    @Column(name="rno")
    private Integer no;

    @Column(name = "empid")
    private String empid;

    @Column(name="rday")
    @CurrentTimestamp
    private Date day;

    @Column(name="rcontent")
    private String content;

    @Column(name = "rdetail")
    private String detail;

    @Column(name = "rstatus")
    private String status;
}
