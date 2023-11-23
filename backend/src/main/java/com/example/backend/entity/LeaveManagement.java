package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@Entity
@DynamicInsert
@Table(name="leavemanagement")
public class LeaveManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lmno;

    @Column(name = "empid")
    private String empid;

    @Column(name = "lmtotal")
    @Builder.Default
    private int lmtotal;

    @Column(name = "lmuse")
    private int lmuse;

    @ManyToOne
    @JoinColumn(name = "empid", insertable=false, updatable=false)
    private Employee employee;
}
