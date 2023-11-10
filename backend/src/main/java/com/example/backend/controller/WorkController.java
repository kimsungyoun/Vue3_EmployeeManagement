package com.example.backend.controller;

import com.example.backend.entity.Work;
import com.example.backend.repository.WorkRepository;
import com.example.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class WorkController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    WorkRepository workRepository;

    @PostMapping("/api/work")
    public ResponseEntity getList(@RequestBody String workday){
        List <Object[]> list;

        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            Date day2 = dateFormat2.parse(workday);
            list = workRepository.WorkList(day2);

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/api/workreason/{workno}")
    public Work setReason(@PathVariable("workno") int workno){
        Work work = workRepository.findByWorkno(workno);

        return work;
    }
}
