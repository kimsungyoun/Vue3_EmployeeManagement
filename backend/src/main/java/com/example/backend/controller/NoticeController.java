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
        return ResponseEntity.ok(list);
    }

    @GetMapping("/api/noticeView/{nno}")
    public ResponseEntity<?> getView(@PathVariable("nno") int nno){
        Notice notice = noticeRepository.findByNno(nno);
        notice.setHit(notice.getHit()+1);
        noticeRepository.save(notice);

        notice.setEmployee(employeeRepository.findByEmpno(notice.getEmpno()));

        return ResponseEntity.ok(notice);
    }

    @PostMapping("/api/noticeWrite")
    public ResponseEntity<?> addNotice(@RequestBody NoticeDTO dto){
        Notice notice = new Notice();

        notice.setEmpno(dto.getEmpno());
        notice.setTitle(dto.getTitle());
        notice.setContent(dto.getContent());

        noticeRepository.save(notice);
        return ResponseEntity.ok("공지 작성 완료");
    }

    @GetMapping("/api/noticeModify/{nno}")
    public ResponseEntity<?> modifyNotice(@PathVariable(name = "nno")int nno){
        Notice notice = noticeRepository.findByNno(nno);
        return ResponseEntity.ok(notice);
    }

    @PostMapping("/api/noticeUpdate")
    public ResponseEntity<?> updateNotice(@RequestBody NoticeDTO dto){
        Notice notice = noticeRepository.findByNnoAndEmpno(dto.getNno(), dto.getEmpno());
        notice.setTitle(dto.getTitle());
        notice.setContent(dto.getContent());
        noticeRepository.save(notice);
        return ResponseEntity.ok("공지 게시판 수정 완료");
    }
}
