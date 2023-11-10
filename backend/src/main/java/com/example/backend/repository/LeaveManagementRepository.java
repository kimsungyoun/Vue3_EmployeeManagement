package com.example.backend.repository;

import com.example.backend.entity.LeaveManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveManagementRepository extends JpaRepository<LeaveManagement,Integer> {

    LeaveManagement findByEmpid(String id);

    List <LeaveManagement> findByEmpidLike(String empid);
}
