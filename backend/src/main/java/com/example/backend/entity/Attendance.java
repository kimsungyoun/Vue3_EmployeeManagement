package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attno;

    @Column(length = 100)
    private String empid;

    @Column
    private String attstatus;

    @Column
    private Time atton;

    @Column
    private Time attoff;

    @Column
    private Date attday;

    @Column
    private Time attwork;
}
