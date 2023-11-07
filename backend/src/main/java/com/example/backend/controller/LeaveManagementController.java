package com.example.backend.controller;

import com.example.backend.entity.LeaveManagement;
import com.example.backend.repository.LeaveManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeaveManagementController {

    @Autowired
    LeaveManagementRepository leaveManagementRepository;

    @GetMapping("/api/leaveManagement")
    public List getList(){
        List Managementlist = leaveManagementRepository.findAll();
        return Managementlist;
    }
    
}
