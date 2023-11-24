package com.example.backend.repository;

import com.example.backend.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    Page<Request> findByStatusLike(String status, Pageable pageable);
    Page<Request> findByStatusNotLike(String status, Pageable pageable);

    List<Request> findByStatusNotLikeAndEmpid(String status, String empid);
}
