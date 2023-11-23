package com.example.backend.entity;

import com.example.backend.dto.NoticeDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Getter
@Setter
@Entity
@DynamicInsert
@Table(name="notice")
public class Notice {
    @Id
    @Column(name = "nno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nno;

    @Column(name="empid")
    private String empid;

    @Column(name="title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "hit")
    private int hit;

    @Column(name = "writedate")
    @CreationTimestamp
    private Date writedate;

    @ManyToOne
    @JoinColumn(name = "empid", insertable=false, updatable=false)
    Employee employee;

    public Notice(NoticeDTO dto) {
        this.empid = dto.getEmpid();
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    public Notice() {

    }
}
