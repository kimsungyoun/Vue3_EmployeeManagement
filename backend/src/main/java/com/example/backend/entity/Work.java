package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@Entity
@ToString
@Table(name="work")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workno;

    @Column(length = 100)
    private String empid;

    @Column
    private String workstatus;

    @Column
    private Time workon;

    @Column
    private Time workoff;

    @Column
    private Date workday;

    @Column
    private Time worktime;
}
