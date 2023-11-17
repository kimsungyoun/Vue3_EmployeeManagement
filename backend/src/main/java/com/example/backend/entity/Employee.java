package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @Column(name = "emp_id")
    private String empid;

    @Column(name = "emp_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empno;

    @Column(name = "emp_name")
    private String empname;

    @Column(name = "emp_birth")
    private String empbirth;

    @Column(name = "emp_postal")
    private String emppostal;

    @Column(name = "emp_addr")
    private String empaddr;

    @Column(name = "emp_detail")
    private String empdetail;

    @Column(name = "emp_phone")
    private String empphone;

    @Column(name = "emp_dept")
    private String empdept;

    @Column(name = "emp_rule")
    private String emprule;

    @Column(name = "emp_hiredate")
    @CreationTimestamp
    private Date emphiredate;

    @Column(name = "emp_password")
    private String password;

    @Column(name = "emp_position")
    @Builder.Default
    private String position;

    @Column(name = "emp_delstatus")
    @Builder.Default
    private String delstatus;
}