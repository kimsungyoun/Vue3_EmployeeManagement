package com.example.backend.controller;

import com.example.backend.CombineData;
import com.example.backend.dto.EmployeeDTO;
import com.example.backend.dto.LeavemanagementDTO;
import com.example.backend.entity.Employee;
import com.example.backend.entity.LeaveManagement;
import com.example.backend.repository.WorkRepository;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.LeaveManagementRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List getList(){
        List list = leaveManagementRepository.findAll();

        return list;
    }

    @GetMapping("/api/employeeInfo/{empid}")
    public LeaveManagement getView(@PathVariable("empid") String empId){
        LeaveManagement leaveManagement = leaveManagementRepository.findByEmpid(empId);

        return leaveManagement;
    }

    @GetMapping("/api/employeeSearch/{keyword}/{searchKey}")
    public ResponseEntity SearchList(@PathVariable("keyword")String keyword, @PathVariable("searchKey")String searchKey){
        try{
            List result = null;
            
            if(Objects.equals(keyword, "empname")){
                result = leaveManagementRepository.findByEmployeeEmpnameLike("%"+searchKey+"%");
            }else if(Objects.equals(keyword, "empdept")){
                result = leaveManagementRepository.findByEmployeeEmpdeptLike("%"+searchKey+"%");
            }else if(Objects.equals(keyword, "emprule")){
                result = leaveManagementRepository.findByEmployeeEmpruleLike("%"+searchKey+"%");
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/employee/add")
    public ResponseEntity AddEmployee(@RequestBody EmployeeDTO dto){
        Employee employee = employeeRepository.findByEmpid(dto.getId());
        LeaveManagement leaveManagement = leaveManagementRepository.findByEmpid(dto.getId());

        if(employee == null && leaveManagement == null){
            try{
                Employee newEmployee = getEmployee(dto);
                employeeRepository.saveAndFlush(newEmployee);

                return new ResponseEntity<>(HttpStatus.OK);
            }catch (Exception e){
                System.out.println("Error Content >> "+e);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @NotNull
    private static Employee getEmployee(EmployeeDTO dto) {
        Employee newEmployee = new Employee();

        newEmployee.setEmpid(dto.getId());
        newEmployee.setPassword(dto.getPassword());
        newEmployee.setEmpname(dto.getName());
        newEmployee.setEmpbirth(dto.getBirth());
        newEmployee.setEmpphone(dto.getPhone());
        newEmployee.setEmppostal(dto.getPostal());
        newEmployee.setEmpaddr(dto.getAddress());
        newEmployee.setEmpdetail(dto.getDetail()+ dto.getExtra());
        newEmployee.setEmpdept(dto.getDept());
        newEmployee.setEmprule(dto.getRule());
        return newEmployee;
    }

    @PostMapping("/api/employeeUpdate")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO dto){
        Employee employee = employeeRepository.findByEmpid(dto.getId());

        employee.setEmpname(dto.getName());
        employee.setEmpdept(dto.getDept());
        employee.setEmprule(dto.getRule());
        employee.setEmpphone(dto.getPhone());
        employee.setEmppostal(dto.getPostal());
        employee.setEmpaddr(dto.getAddress());
        employee.setEmpdetail(dto.getDetail());

        employeeRepository.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/employee/delete/{empid}")
    public ResponseEntity removeEmployee(@PathVariable("empid") String empId){
        Employee employee = employeeRepository.findByEmpid(empId);

        if(employee != null){
            employeeRepository.delete(employee);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
