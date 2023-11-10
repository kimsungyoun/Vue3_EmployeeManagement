package com.example.backend.repository;

import com.example.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmpid(String empid);
    Employee findByEmpidAndPassword(String empid, String password);
    List <Employee> findByEmpnameLike(String empname);
    List findByEmpdeptLike(String empdept);
    List findByEmpruleLike(String emprule);

    @Query("SELECT e, lm FROM Employee e INNER JOIN LeaveManagement lm ON e.empid = lm.empid order by e.empname")
    List <Object[]> Employeelist();
}
