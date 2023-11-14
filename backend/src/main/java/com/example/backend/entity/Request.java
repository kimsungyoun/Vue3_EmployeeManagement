package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="r_no")
    private Integer no;

    @Column(name="workno")
    private Integer workno;

    @Column(name="r_reason")
    private String reason;

    @Column(name="r_result")
    private String result;

    @Column(name = "w_day")
    private Date workday;

    @Column(name = "r_day")
    private Date requestday;
}
