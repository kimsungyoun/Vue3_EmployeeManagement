package com.example.backend.repository;

import com.example.backend.dto.EmployeeDTO;
import com.example.backend.entity.Employee;
import io.vavr.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmpid(String empid);

    Employee findByEmpidAndPassword(String empid,String password);

    @Query("select * from Employee e inner join LeaveManagement lm WHERE e.empid = lm.empid")
    List getList();
}
