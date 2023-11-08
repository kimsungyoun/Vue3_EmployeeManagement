package com.example.backend.controller;

import com.example.backend.CombineData;
import com.example.backend.dto.EmployeeDTO;
import com.example.backend.entity.Employee;
import com.example.backend.entity.LeaveManagement;
import com.example.backend.repository.WorkRepository;
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
    WorkRepository attendanceRepository;

    @GetMapping("/api/employee")
    public List getList(){
        List result = new ArrayList<>();

        List<Employee> employees = employeeRepository.findAll();
        List<LeaveManagement> leaveManagements = leaveManagementRepository.findAll();

        for(Employee e : employees){
            for(LeaveManagement l : leaveManagements){
                if(e.getEmpid().equals(l.getEmpid())){
                    List newList = new ArrayList();
                    newList.add(e);
                    newList.add(l);
                    result.add(newList);
                }
            }
        }

        return result;
    }

    @GetMapping("/api/employeeSearch/{keyword}/{searchKey}")
    public ResponseEntity SearchList(@PathVariable("keyword")String keyword, @PathVariable("searchKey")String searchKey){
        List result = new ArrayList();

        List<Employee> emp = null;

        if(keyword.equals("empname")){
            emp = employeeRepository.findByEmpnameLike("%"+searchKey+"%");
        }else if(keyword.equals("empdept")){
            emp = employeeRepository.findByEmpdeptLike("%"+searchKey+"%");
        }else if(keyword.equals("emprule")) {
            emp = employeeRepository.findByEmpruleLike("%" + searchKey + "%");
        }
        for(Employee e : emp){
            List <LeaveManagement> lm = leaveManagementRepository.findByEmpidLike("%"+e.getEmpid()+"%");
            for(LeaveManagement l : lm){
                if(e.getEmpid().equals(l.getEmpid())){
                    List list1 = new ArrayList();
                    list1.add(e);
                    list1.add(l);
                    result.add(list1);
                }
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/api/employeeInfo/{empid}")
    public CombineData getView(@PathVariable("empid") String empId){
        Employee employee = employeeRepository.findByEmpid(empId);
        LeaveManagement leaveManagement = leaveManagementRepository.findByEmpid(empId);

        return new CombineData(employee, leaveManagement);
    }

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

                newEmployee.setEmpdetail(dto.getDetail()+dto.getExtra());
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
