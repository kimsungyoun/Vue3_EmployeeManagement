package com.example.backend.repository;

import com.example.backend.entity.Employee;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmpid(String empid);
    Employee findByEmpidAndPassword(String empid, String password);
    Page<Employee> findByEmpnameLike(String s, Pageable pageable);
    Page<Employee> findByEmpdeptLike(String s, Pageable pageable);
    Page<Employee> findByEmpruleLike(String s, Pageable pageable);
}
