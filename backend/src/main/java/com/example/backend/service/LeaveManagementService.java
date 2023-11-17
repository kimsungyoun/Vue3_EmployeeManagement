package com.example.backend.service;

import com.example.backend.repository.LeaveManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveManagementService {
    @Autowired
    private LeaveManagementRepository leaveManagementRepository;

}
