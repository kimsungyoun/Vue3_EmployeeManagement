package com.example.backend.controller;

import com.example.backend.dto.RequestDTO;
import com.example.backend.entity.Employee;
import com.example.backend.entity.LeaveManagement;
import com.example.backend.entity.Request;
import com.example.backend.entity.Work;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.LeaveManagementRepository;
import com.example.backend.repository.RequestRepository;
import com.example.backend.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class RequestController {
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    LeaveManagementRepository leaveManagementRepository;

    @Autowired
    WorkRepository workRepository;

    @GetMapping("/api/request")
    public ResponseEntity<?> getList(@PageableDefault(sort = "no", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Request> list = requestRepository.findByStatusLike("진행중", pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/api/request2")
    public ResponseEntity<?> getList2(@PageableDefault(sort = "no", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Request> list = requestRepository.findByStatusNotLike("진행중", pageable);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/api/requestWrite")
    public ResponseEntity<?> writeRequest(@RequestBody RequestDTO dto) {
        Employee employee = employeeRepository.findByEmpid(dto.getEmpid());

        if (employee != null) {
            Request request = new Request();
            request.setEmpid(employee.getEmpid());
            request.setDay(dto.getDay());
            request.setContent(dto.getContent());
            request.setDetail(dto.getDetail());
            request.setEmployee(employee);

            requestRepository.save(request);
            return ResponseEntity.ok("요청 작성 완료");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Request Write Error");
    }

    @GetMapping("/api/requestList/{empid}")
    public ResponseEntity<?> getRequest(@PathVariable("empid") String empId) {
        List<Request> request = requestRepository.findByStatusNotLikeAndEmpid("처리완료", empId);

        return ResponseEntity.ok(request);
    }

    @GetMapping("/api/requestDetail/{rno}")
    public ResponseEntity<?> getRequestDetail(@PathVariable("rno") int rno) {
        Request request = requestRepository.findByNo(rno);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/api/requestUpdate")
    public ResponseEntity<?> updateRequest(@RequestBody RequestDTO dto) {
        Request request = requestRepository.findByNo(dto.getNo());
        request.setStatus(dto.getStatus());
        requestRepository.save(request);

        LeaveManagement leaveManagement = leaveManagementRepository.findByEmpid(dto.getEmpid());

        if (Objects.equals(dto.getStatus(), "처리완료")) {
            if (Objects.equals(dto.getContent(), "연차")) {
                leaveManagement.setLmuse(leaveManagement.getLmuse() + 1);
                Work work = new Work();
                work.setEmpid(dto.getEmpid());
                work.setWorkstatus(dto.getContent());
                work.setWorkon(null);
                work.setWorkoff(null);
                work.setWorkday(dto.getDay());
                leaveManagementRepository.save(leaveManagement);
                workRepository.save(work);
            } else if (Objects.equals(dto.getContent(), "오전연차")) {
                leaveManagement.setLmuse(leaveManagement.getLmuse() + 0.5);
                Work work = new Work();
                work.setEmpid(dto.getEmpid());
                work.setWorkstatus(dto.getContent());
                work.setWorkon(null);
                work.setWorkoff(null);
                work.setWorkday(dto.getDay());
                leaveManagementRepository.save(leaveManagement);
                workRepository.save(work);
            } else if (Objects.equals(dto.getContent(), "오후연차")) {
                leaveManagement.setLmuse(leaveManagement.getLmuse() + 0.5);
                Work work = new Work();
                work.setEmpid(dto.getEmpid());
                work.setWorkstatus(dto.getContent());
                work.setWorkon(null);
                work.setWorkoff(null);
                work.setWorkday(dto.getDay());
                leaveManagementRepository.save(leaveManagement);
                workRepository.save(work);
            } else if(Objects.equals(dto.getContent(), "외근")){
                Work work = new Work();
                work.setEmpid(dto.getEmpid());
                work.setWorkstatus(dto.getContent());
                work.setWorkon(null);
                work.setWorkoff(null);
                work.setWorkday(dto.getDay());
                workRepository.save(work);
            }
            return ResponseEntity.ok("요청에 대한 처리 완료");
        }else if(Objects.equals(dto.getStatus(), "취소") || Objects.equals(dto.getStatus(), "진행중")){
            return ResponseEntity.ok("요청에 대해서 취소 처리");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request Update Error");
    }
}


