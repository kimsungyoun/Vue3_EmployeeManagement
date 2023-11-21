package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Entity
@DynamicInsert
@Table(name="request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rno")
    private Integer no;

    @Column(name = "empid" , insertable=false, updatable=false)
    private String empid;

    @Column(name="rday")
    private Date day;

    @Column(name="rcontent")
    private String content;

    @Column(name = "rdetail")
    private String detail;

    @Column(name = "rstatus")
    private String status;

    @ManyToOne
    @JoinColumn(name = "empid")
    Employee employee;
}
