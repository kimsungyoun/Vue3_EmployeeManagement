package com.example.backend.controller;

import com.example.backend.entity.Employee;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JwtService jwtService;

    /* 로그인 : 쿠키를 생성 */
    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params, HttpServletResponse res){
        Employee employee = employeeRepository.findByEmpidAndPassword(params.get("email"), params.get("password"));

        if(employee != null){
            int id = employee.getEmpno();
            String token = jwtService.getToken("id", id);
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            res.addCookie(cookie);

            /*String position = employee.getPosition();
            String token2 = jwtService.getToken("position", position);
            Cookie cookie1 = new Cookie("token2", token2);
            res.addCookie(cookie1);

            List <Object> list = new ArrayList<>();
            list.add(id);
            list.add(position);*/

            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    /* 로그아웃 : 쿠키 초기화 */
    @PostMapping("/api/account/logout")
    public ResponseEntity logout(HttpServletResponse res) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        res.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* 토큰 유효성 검사 */
    @GetMapping("/api/account/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token){
        Claims claims = jwtService.getClaims(token);

        if(claims != null){
            int id = Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(id,HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /* 아이디 중복 체크 */
    @GetMapping("/api/checkIdDuplication/{id}")
    public int checkId(@PathVariable("id")String empid){
        Employee employee = employeeRepository.findByEmpid(empid);
        if(employee != null){
            return 1;
        }
        return 0;
    }

}
