package com.example.backend.controller;

import com.example.backend.dto.NoticeDTO;
import com.example.backend.entity.Notice;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class NoticeController {
    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/api/noticeList")
    public ResponseEntity<?> getList(@PageableDefault(sort="nno", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Notice> list = noticeRepository.findAll(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/api/noticeView/{nno}")
    public ResponseEntity<?> getView(@PathVariable("nno") int nno){
        Notice notice = noticeRepository.findByNno(nno);
        notice.setHit(notice.getHit()+1);
        noticeRepository.save(notice);

        notice.setEmployee(employeeRepository.findByEmpno(notice.getEmpno()));

        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    @PostMapping("/api/noticeWrite")
    public ResponseEntity<?> addNotice(@RequestBody NoticeDTO dto){
        Notice notice = new Notice();

        notice.setEmpno(dto.getEmpno());
        notice.setTitle(dto.getTitle());
        notice.setContent(dto.getContent());

        noticeRepository.save(notice);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/noticeModify")
    public ResponseEntity<?> modifyNotice(@RequestBody NoticeDTO dto){
        Notice notice = noticeRepository.findByNnoAndEmpno(dto.getNno(), dto.getEmpno());

        notice.setEmpno(dto.getEmpno());
        notice.setTitle(dto.getTitle());
        notice.setContent(dto.getContent());

        noticeRepository.save(notice);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
