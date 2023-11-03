package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="leavemanagement")
public class LeaveManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lmno;

    @Column(name = "empid")
    private String empid;

    @Column(name = "lmtotal")
    private int lmtotal;

    @Column(name = "lmremain")
    private int lmremain;


}
