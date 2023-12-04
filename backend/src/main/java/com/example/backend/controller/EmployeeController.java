package com.example.backend.controller;

import com.example.backend.dto.EmployeeDTO;
import com.example.backend.entity.Employee;
import com.example.backend.entity.LeaveManagement;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.LeaveManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    LeaveManagementRepository leaveManagementRepository;

    @GetMapping("/api/employee")
    public ResponseEntity <?> getList(@PageableDefault(sort = "empname") Pageable pageable){
        Page <Employee> list = employeeRepository.findByPositionNotLike("a", pageable);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/api/employeeInfo/{empid}")
    public LeaveManagement getView(@PathVariable("empid") String empId){
        return leaveManagementRepository.findByEmpid(empId);
    }

    @GetMapping("/api/employeeSearch/{keyword}/{searchKey}")
    public ResponseEntity<?> SearchList(@PathVariable("keyword")String keyword,@PathVariable("searchKey")String searchKey,@PageableDefault(sort = "empname") Pageable pageable){
        try{
            Page<?> result = null;
            if(Objects.equals(keyword, "empname")){
                result = employeeRepository.findByEmpnameLike("%"+searchKey+"%", pageable);
            }else if(Objects.equals(keyword, "empdept")){
                result = employeeRepository.findByEmpdeptLike("%"+searchKey+"%", pageable);
            }else if(Objects.equals(keyword, "emprule")){
                result = employeeRepository.findByEmpruleLike("%"+searchKey+"%", pageable);
            }

            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check your Keyword and Search Key");
        }
    }

    @PostMapping("/api/employeeUpdate")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO dto){
        Employee employee = employeeRepository.findByEmpid(dto.getEmpid());
        if(employee != null){
            employee.setEmpname(dto.getEmpname());
            employee.setEmpdept(dto.getEmpdept());
            employee.setEmprule(dto.getEmprule());
            employee.setEmpbirth(dto.getEmpbirth());
            employee.setEmpphone(dto.getEmpphone());
            employee.setEmppostal(dto.getEmppostal());
            employee.setEmpaddr(dto.getEmpaddr());
            employee.setEmpdetail(dto.getEmpdetail());

            employeeRepository.save(employee);
            return ResponseEntity.ok("수정 완료");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("수정 실패");
    }

    @PostMapping("/api/employee/add")
    public ResponseEntity<?> AddEmployee(@RequestBody EmployeeDTO dto){
        if(employeeRepository.findByEmpid(dto.getEmpid()) == null){
            Employee employee = new Employee(dto);
            employeeRepository.save(employee);
            return ResponseEntity.ok("등록 완료");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/employee/delete/{empid}")
    public ResponseEntity<?> removeEmployee(@PathVariable("empid") String empId){
        Employee employee = employeeRepository.findByEmpid(empId);
        if(employee != null){
            employee.setDelstatus("o");
            employeeRepository.save(employee);
            return ResponseEntity.ok("회원 탈퇴");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Error");
    }
}
