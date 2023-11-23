package com.example.backend.repository;

import com.example.backend.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Notice findByNno(int nno);
    Notice findByNnoAndEmpno(int nno, int empno);
    Notice findByEmployeeEmpno(int empno);
}
