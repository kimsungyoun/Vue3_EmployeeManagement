package com.example.backend.repository;

import com.example.backend.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface WorkRepository extends JpaRepository<Work,Integer> {

    List<Work> findByEmpidAndWorkday(String empid, Date workday);

    Work findByWorkno(int workno);
}
