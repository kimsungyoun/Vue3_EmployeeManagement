package com.example.backend.controller;

import com.example.backend.dto.NoticeDTO;
import com.example.backend.entity.Notice;
import com.example.backend.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NoticeController {
    @Autowired
    NoticeRepository noticeRepository;

    @GetMapping("/api/noticeList")
    public ResponseEntity<?> getList(Pageable pageable){
        Page<Notice> list = noticeRepository.findAll(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/api/noticeView/{nno}")
    public ResponseEntity<?> getView(@PathVariable("nno") int nno){
        Notice notice = noticeRepository.findByNno(nno);
        notice.setHit(+1);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    @PostMapping("/api/noticeWrite")
    public ResponseEntity<?> addNotice(NoticeDTO dto){
        Notice notice = new Notice(dto);
        noticeRepository.save(notice);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/noticeModify")
    public ResponseEntity<?> modifyNotice(NoticeDTO dto){
        Notice notice = noticeRepository.findByNno(dto.getNno());
        notice.setTitle(dto.getTitle());
        notice.setContent(dto.getTitle());
        noticeRepository.save(notice);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
