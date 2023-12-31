package com.example.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.annotation.Nullable;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@Entity
@DynamicInsert
@Table(name="work")
public class Work {
    @Id
    @Column(name = "workno")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int workno;

    @Column(name = "empid")
    private String empid;

    @Column(name="workstatus")
    private String workstatus;

    @Column(name="workon")
    private Time workon;

    @Column(name="workoff")
    private Time workoff;

    @Column(name = "workday")
    private Date workday;

    @Column(name="worktime")
    private Time worktime;

    @ManyToOne
    @JoinColumn(name="empid", insertable=false, updatable=false)
    private Employee employee;
}
