package com.example.backend.controller;

import com.example.backend.entity.Employee;
import com.example.backend.entity.Work;
import com.example.backend.repository.WorkRepository;
import com.example.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
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
    public List getList(@RequestBody String workday){
        /*List result = new ArrayList();

        try{
            List <Employee> employeeList = employeeRepository.findAll();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date day = dateFormat.parse(workday);

            for(Employee e : employeeList){
                List <Work> workList = workRepository.findByEmpidAndWorkday(e.getEmpid(), day);
                for (Work w :workList){
                    if(w!=null){
                        if(e.getEmpid().equals(w.getEmpid())) {
                            List list = new ArrayList();
                            list.add(e);
                            list.add(w);
                            result.add(list);
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println("error >> " + e);
        }*/

        List <Object[]> list = new ArrayList<>();

        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            Date day2 = dateFormat2.parse(workday);
            list = workRepository.WorkList(day2);
            for(Object o : list){
                System.out.println(o);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @GetMapping("/api/workreason/{workno}")
    public Work setReason(@PathVariable("workno") int workno){
        Work work = workRepository.findByWorkno(workno);
        return work;

    }
}
