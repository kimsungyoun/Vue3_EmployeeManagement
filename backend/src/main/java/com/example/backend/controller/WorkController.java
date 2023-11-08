package com.example.backend.controller;

import com.example.backend.repository.WorkRepository;
import com.example.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WorkController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    WorkRepository workRepository;

    @GetMapping("/api/work")
    public List getList(){
        List list = new ArrayList();
        list.add(workRepository.findAll());
        return list;
    }

}
