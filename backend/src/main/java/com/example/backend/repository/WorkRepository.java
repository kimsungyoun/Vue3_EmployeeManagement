package com.example.backend.repository;

import com.example.backend.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work,Integer> {
    Work findByWorkno(int workno);
    List <Work> findByWorkday(Date workday);
}
