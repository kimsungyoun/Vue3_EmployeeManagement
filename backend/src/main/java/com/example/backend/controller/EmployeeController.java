package com.example.backend.controller;

import com.example.backend.dto.EmployeeDTO;
import com.example.backend.entity.Employee;
import com.example.backend.entity.LeaveManagement;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.LeaveManagementRepository;
import com.example.backend.service.LeaveManagementService;
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

    @Autowired
    LeaveManagementService leaveManagementService;

    /*
    @GetMapping("/api/employee")
    public List <?> getList(){
        return employeeRepository.findAll();
    }
    */

    @GetMapping("/api/employee")
    public ResponseEntity <?> getList2(@PageableDefault(sort = "empname") Pageable pageable){
        return new ResponseEntity<>(employeeRepository.findAll(pageable),HttpStatus.OK);
    }

    @GetMapping("/api/employeeInfo/{empid}")
    public LeaveManagement getView(@PathVariable("empid") String empId){
        return leaveManagementRepository.findByEmpid(empId);
    }

    @GetMapping("/api/employeeSearch/{keyword}/{searchKey}")
    public ResponseEntity<?> SearchList(@PathVariable("keyword")String keyword,
                                        @PathVariable("searchKey")String searchKey,
                                        @PageableDefault(sort = "empname") Pageable pageable)
    {
        try{
            //List <?> result = null;
            Page<?> result = null;
            if(Objects.equals(keyword, "empname")){
                result = employeeRepository.findByEmpnameLike("%"+searchKey+"%", pageable);
            }else if(Objects.equals(keyword, "empdept")){
                result = employeeRepository.findByEmpdeptLike("%"+searchKey+"%", pageable);
            }else if(Objects.equals(keyword, "emprule")){
                result = employeeRepository.findByEmpruleLike("%"+searchKey+"%", pageable);
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/employeeUpdate")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO dto){
        Employee employee = employeeRepository.findByEmpid(dto.getEmpid());

        if(employee != null){
            employee.setEmpname(dto.getEmpname());
            employee.setEmpdept(dto.getEmpdept());
            employee.setEmprule(dto.getEmprule());
            employee.setEmpphone(dto.getEmpphone());
            employee.setEmppostal(dto.getEmppostal());
            employee.setEmpaddr(dto.getEmpaddr());
            employee.setEmpdetail(dto.getEmpdetail());

            employeeRepository.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/employee/add")
    public ResponseEntity<?> AddEmployee(@RequestBody EmployeeDTO dto){
        if(employeeRepository.findByEmpid(dto.getEmpid()) == null){
            Employee employee = new Employee(dto);

            employeeRepository.save(employee);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/employee/delete/{empid}")
    public ResponseEntity<?> removeEmployee(@PathVariable("empid") String empId){
        Employee employee = employeeRepository.findByEmpid(empId);

        if(employee != null){
            employeeRepository.delete(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
