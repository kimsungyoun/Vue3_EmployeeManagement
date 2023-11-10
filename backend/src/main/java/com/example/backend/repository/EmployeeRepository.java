package com.example.backend.repository;

import com.example.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmpid(String empid);
    Employee findByEmpidAndPassword(String empid,String password);
    List <Employee> findByEmpnameLike(String empname);
    List findByEmpdeptLike(String empdept);
    List findByEmpruleLike(String emprule);

    @Query("SELECT Employee FROM Employee INNER JOIN LeaveManagement ON Employee.empid = LeaveManagement.empid")
    List Employeelist();
}
