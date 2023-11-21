package com.example.backend.controller;

import com.example.backend.dto.RequestDTO;
import com.example.backend.entity.Employee;
import com.example.backend.entity.Request;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.RequestRepository;
import org.checkerframework.checker.units.qual.A;
import org.mapstruct.ap.shaded.freemarker.template.TemplateMethodModelEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RequestController {
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/api/request")
    public ResponseEntity<?> getList (@PageableDefault(sort = "no",direction = Sort.Direction.DESC) Pageable pageable){
        Page<Request> list = requestRepository.findAll(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/api/requestWrite")
    public ResponseEntity<?> writeRequest(@RequestBody RequestDTO dto){
        Employee employee = employeeRepository.findByEmpno(dto.getId());
        if(employee !=null){
            Request request = new Request();

            request.setEmpid(employee.getEmpid());
            request.setDay(dto.getDay());
            request.setContent(dto.getContent());
            request.setDetail(dto.getDetail());

            requestRepository.save(request);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
