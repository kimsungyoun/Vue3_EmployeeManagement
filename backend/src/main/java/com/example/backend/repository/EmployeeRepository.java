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

    @Query("select e.empid, e.empname, e.empbirth,e.empphone, e.empdept, e.emprule,e.emphiredate, l.lmtotal, l.lmuse " +
            "from Employee e, LeaveManagement l " +
            "where e.empid = l.empid")
    List getList();
}
