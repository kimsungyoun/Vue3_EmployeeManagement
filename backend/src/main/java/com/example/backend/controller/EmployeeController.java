package com.example.backend.controller;

import com.example.backend.CombineData;
import com.example.backend.dto.EmployeeDTO;
import com.example.backend.entity.Attendance;
import com.example.backend.entity.Employee;
import com.example.backend.entity.LeaveManagement;
import com.example.backend.repository.AttendanceRepository;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.LeaveManagementRepository;
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

    @Autowired
    AttendanceRepository attendanceRepository;

    /*
    @GetMapping("/api/employee")
    public List<CombineData> getList(){
        List<Employee> Employeelist = employeeRepository.findAll();
        List<LeaveManagement> leaveManagementsList = leaveManagementRepository.findAll();
        List<CombineData> list = new ArrayList<>();

        for (Employee value : Employeelist) {
            for (LeaveManagement management : leaveManagementsList) {
                if (value.getEmpid().equals(management.getEmpid())) {
                    CombineData combineData = new CombineData(value, management);
                    list.add(combineData);
                }
            }
        }
        return list;
    }
    */

    @GetMapping("/api/employee")
    public List getList(){
        List list = employeeRepository.getList();
        return list;
    }

    @GetMapping("/api/employeeSearch/{keyword}/{searchKey}")
    public List SearchList(@PathVariable("keyword")String keyword, @PathVariable("searchKey")String searchKey){
        List list = employeeRepository.searchList(keyword, searchKey);

        return list;
    }

    @GetMapping("/api/employeeInfo/{empid}")
    public CombineData getView(@PathVariable("empid") String empId){
        Employee employee = employeeRepository.findByEmpid(empId);
        LeaveManagement leaveManagement = leaveManagementRepository.findByEmpid(empId);
        CombineData combineData = new CombineData(employee, leaveManagement);

        return combineData;
    }

    /*
    @GetMapping("/api/employeeInfo2/{empid}")
    public List getView2(@PathVariable("empid") String empId){
        List list = employeeRepository.getView(empId);

        return list;
    }
    */

    @PostMapping("/api/employee/add")
    public ResponseEntity AddEmployee(@RequestBody EmployeeDTO dto){
        Employee employee = employeeRepository.findByEmpid(dto.getId());
        LeaveManagement leaveManagement = leaveManagementRepository.findByEmpid(dto.getId());

        if(employee == null || leaveManagement == null){
            try{
                Employee newEmployee = new Employee();
                newEmployee.setEmpid(dto.getId());
                newEmployee.setPassword(dto.getPassword());
                newEmployee.setEmpname(dto.getName());
                newEmployee.setEmpbirth(dto.getBirth());
                newEmployee.setEmpphone(dto.getPhone());
                newEmployee.setEmppostal(dto.getPostal());
                newEmployee.setEmpaddr(dto.getAddress());
                newEmployee.setEmpdetail(dto.getDetail());
                newEmployee.setEmpdept(dto.getDept());
                newEmployee.setEmprule(dto.getRule());
                employeeRepository.saveAndFlush(newEmployee);

                LeaveManagement newLV = new LeaveManagement();
                newLV.setEmpid(dto.getId());
                newLV.setLmtotal(15);
                leaveManagementRepository.saveAndFlush(newLV);

                return new ResponseEntity<>(HttpStatus.OK);
            }catch (Exception e){
                System.out.println("Error Content >> "+e);
            }

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/api/employeeUpdate")
    public void updateEmployee(@RequestBody EmployeeDTO dto){
        Employee employee = employeeRepository.findByEmpid(dto.getId());

        employee.setEmpname(dto.getName());
        employee.setEmpdept(dto.getDept());
        employee.setEmprule(dto.getRule());
        employee.setEmpphone(dto.getPhone());
        employee.setEmppostal(dto.getPostal());
        employee.setEmpaddr(dto.getAddress());
        employee.setEmpdetail(dto.getDetail());

        employeeRepository.save(employee);
    }

    @DeleteMapping("/api/employee/delete/{empid}")
    public void removeEmployee(@PathVariable("empid") String empId){
        Employee employee = employeeRepository.findByEmpid(empId);

        if(employee != null){
            employeeRepository.delete(employee);
        }
    }
}
