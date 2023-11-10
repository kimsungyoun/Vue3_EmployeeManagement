package com.example.backend.repository;

import com.example.backend.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work,Integer> {

    List<Work> findByEmpidAndWorkday(String empid, Date workday);

    Work findByWorkno(int workno);

    @Query("select e, w from Employee e inner join Work w on e.empid=w.empid WHERE w.workday = :workday")
    List <Object[]> WorkList(Date workday);
}
