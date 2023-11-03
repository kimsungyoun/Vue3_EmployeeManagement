package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @Column(name = "empid")
    private String empid;

    @Column(name = "empno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empno;

    @Column(name = "empname")
    private String empname;

    @Column(name = "empbirth")
    private String empbirth;

    @Column(name = "emppostal")
    private String emppostal;

    @Column(name = "empaddr")
    private String empaddr;

    @Column(name = "empdetail")
    private String empdetail;

    @Column(name = "empphone")
    private String empphone;

    @Column(name = "empdept")
    private String empdept;

    @Column(name = "emprule")
    private String emprule;

    @Column(name = "emphiredate")
    @CreationTimestamp
    private Date emphiredate;

    @Column(name = "password")
    private String password;
}