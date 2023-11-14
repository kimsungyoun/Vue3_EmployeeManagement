package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="leavemanagement")
public class LeaveManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lmno;

    @Column(name = "empid", insertable=false, updatable=false)
    private String empid;

    @Column(name = "lmtotal")
    @Builder.Default
    private int lmtotal;

    @Column(name = "lmuse")
    private int lmuse;

    @ManyToOne
    @JoinColumn(name = "empid")
    private Employee employee;
}
