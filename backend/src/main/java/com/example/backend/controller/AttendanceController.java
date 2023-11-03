package com.example.backend.controller;

import com.example.backend.entity.Attendance;
import com.example.backend.entity.Employee;
import com.example.backend.repository.AttendanceRepository;
import com.example.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class AttendanceController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @GetMapping("/api/attendance")
    public void getList(){
        List <Attendance> attendanceList = attendanceRepository.findAll();
        List <Employee> employeesList = new ArrayList<>();


    }

}
