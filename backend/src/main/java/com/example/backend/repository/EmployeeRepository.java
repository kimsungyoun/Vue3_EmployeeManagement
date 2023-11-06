package com.example.backend.repository;

import com.example.backend.dto.EmployeeDTO;
import com.example.backend.entity.Employee;
import io.vavr.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmpid(String empid);

    Employee findByEmpidAndPassword(String empid,String password);

    @Query("select e,lm from Employee e inner join LeaveManagement lm on e.empid = lm.empid")
    List getList();

    @Query("select e, lm from Employee e inner join LeaveManagement lm on e.empid=lm.empid and e.empid=:empid")
    List getView(String empid);

    @Query("select e, lm from Employee e inner join LeaveManagement lm on e.empid=lm.empid where :keyword like concat('%',:searchKey,'%')")
    List searchList(String keyword, String searchKey);
}
