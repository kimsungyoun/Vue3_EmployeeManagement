package com.example.backend.repository;

import com.example.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmpid(String empid);

    Employee findByEmpidAndPassword(String empid,String password);

//    List findAllOrderByEmpnameDesc();

    List <Employee> findByEmpnameLike(String empname);
    List findByEmpdeptLike(String empdept);
    List findByEmpruleLike(String emprule);
}
