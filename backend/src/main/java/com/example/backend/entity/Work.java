package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

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

    @Column(name = "empid", insertable=false, updatable=false)
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
    @JoinColumn(name="empid")
    private Employee employee;
}
