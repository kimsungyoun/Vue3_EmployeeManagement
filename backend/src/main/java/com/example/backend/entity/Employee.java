package com.example.backend.entity;

import com.example.backend.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Getter
@Setter
@Entity
@DynamicInsert
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

    @Column(name = "emppassword")
    private String password;

    @Column(name = "empposition")
    private String position;

    @Column(name = "empdelstatus")
    private String delstatus;

    public Employee(EmployeeDTO dto) {
        this.empid = dto.getEmpid();
        this.password = dto.getPassword();
        this.empname = dto.getEmpname();
        this.empbirth = dto.getEmpbirth();
        this.empphone = dto.getEmpphone();
        this.emppostal = dto.getEmppostal();
        this.empaddr = dto.getEmpaddr();
        this.empdetail = dto.getEmpdetail()+" "+ dto.getExtra();
        this.empdept = dto.getEmpdept();
        this.emprule = dto.getEmprule();
    }

    public Employee() {

    }
}