package com.example.backend.controller;

import com.example.backend.dto.RequestDTO;
import com.example.backend.entity.Request;
import com.example.backend.entity.Work;
import com.example.backend.repository.RequestRepository;
import com.example.backend.repository.WorkRepository;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class RequestController {
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    WorkRepository workRepository;

    @PostMapping("/api/request/reason")
    public ResponseEntity requestWork (@RequestBody RequestDTO dto){
        Request request = new Request();

        try{
            request.setEmpid(dto.getId());
            request.setContent(dto.getContent());
            request.setDetail(dto.getDetail());
            request.setStatus("확인중");

            requestRepository.saveAndFlush(request);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error >> "+  e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
