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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class WorkController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    WorkRepository workRepository;

    @PostMapping("/api/work")
    public ResponseEntity<?> getList(@RequestBody String workday){
        List <Work> list;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date day = dateFormat.parse(workday);
            list = workRepository.findByWorkday(day);
            return ResponseEntity.ok(list);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/api/workreason/{workno}")
    public Work setReason(@PathVariable("workno") int workno){
        return workRepository.findByWorkno(workno);
    }

    @PostMapping("/api/workList")
    public ResponseEntity<?> getWorkList(@RequestBody String searchDay){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date day = dateFormat.parse(searchDay);
            List<Work> list = workRepository.findByWorkday(day);

            return ResponseEntity.ok(list);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
