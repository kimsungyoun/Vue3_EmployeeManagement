package com.example.backend.repository;

import com.example.backend.entity.Employee;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmpid(String empid);
    Employee findByEmpidAndPassword(String empid, String password);

    List<?> findByEmpnameLike(String s);

    List<?> findByEmpdeptLike(String s);

    List<?> findByEmpruleLike(String s);
}
