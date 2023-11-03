package com.example.backend.repository;

import com.example.backend.entity.Attendance;
import com.example.backend.entity.LeaveManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveManagementRepository extends JpaRepository<LeaveManagement,Integer> {

    LeaveManagement findByEmpid(String id);
}
