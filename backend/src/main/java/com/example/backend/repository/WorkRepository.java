package com.example.backend.repository;

import com.example.backend.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work,Integer> {

}
