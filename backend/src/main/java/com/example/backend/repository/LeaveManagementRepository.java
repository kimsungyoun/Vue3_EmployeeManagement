package com.example.backend.repository;

import com.example.backend.dto.LeavemanagementDTO;
import com.example.backend.entity.LeaveManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveManagementRepository extends JpaRepository<LeaveManagement,Integer> {
    LeaveManagement findByEmpid(String id);

}
