package com.example.backend.controller;

import com.example.backend.dto.EmployeeDTO;
import com.example.backend.entity.Employee;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/api/account/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> params, HttpServletResponse res){
        Employee employee = employeeRepository.findByEmpidAndPassword(params.get("email"), params.get("password"));

        if(employee != null){
            int no = employee.getEmpno();
            String id = employee.getEmpid();
            String position = employee.getPosition();

            String token = jwtService.getToken("no", no, "id",id,"position", position);
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            res.addCookie(cookie);

            Map<String, Object> map = new HashMap<>();
            map.put("no", no);
            map.put("id", id);
            map.put("position", position);

            return ResponseEntity.ok(map);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/api/account/logout")
    public ResponseEntity<?> logout(HttpServletResponse res) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* 토큰 유효성 검사 */
    @GetMapping("/api/account/check")
    public ResponseEntity<?> check(@CookieValue(value = "token", required = false) String token){
        Claims claims = jwtService.getClaims(token);
        Map<String, Object> map = new HashMap<>();

        if(claims != null){
            int no = Integer.parseInt(claims.get("no").toString());
            String id = claims.get("id").toString();
            String position = claims.get("position").toString();

            map.put("no", no);
            map.put("id", id);
            map.put("position", position);

            return ResponseEntity.ok(map);
        }
        return ResponseEntity.ok(null);
    }

    /* 아이디 중복 체크 */
    @GetMapping("/api/checkIdDuplication/{id}")
    public int checkId(@PathVariable("id") String empid){
        Employee employee = employeeRepository.findByEmpid(empid);
        if(employee != null){
            return 1;
        }
        return 0;
    }
}
