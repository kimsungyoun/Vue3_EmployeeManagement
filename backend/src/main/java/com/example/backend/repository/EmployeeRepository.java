package com.example.backend.repository;

import com.example.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmpid(String empid);
    Employee findByEmpidAndPassword(String empid, String password);
    List <Employee> findByEmpnameLike(String empname);
    List findByEmpdeptLike(String empdept);
    List findByEmpruleLike(String emprule);

    @Query("SELECT e, lm FROM Employee e INNER JOIN LeaveManagement lm ON e.empid = lm.empid")
    List <Object[]> Employeelist();

    @Query("SELECT e, lm FROM Employee e INNER JOIN LeaveManagement lm ON e.empid = lm.empid where :keyword like concat('%',:searchword,'%')")
    List <Object[]> SearchEmployeeList(String keyword, String searchword);
}
