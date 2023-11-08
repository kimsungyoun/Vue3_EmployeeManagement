package com.example.backend.repository;

import com.example.backend.entity.LeaveManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveManagementRepository extends JpaRepository<LeaveManagement,Integer> {

    LeaveManagement findByEmpid(String id);

    List <LeaveManagement> findByEmpidLike(String empid);
}
