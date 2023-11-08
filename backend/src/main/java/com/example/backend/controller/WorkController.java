package com.example.backend.controller;

import com.example.backend.entity.Employee;
import com.example.backend.entity.Work;
import com.example.backend.repository.WorkRepository;
import com.example.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
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
        List <Employee> employeeList = employeeRepository.findAll();
        List result = new ArrayList();

        try{
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
        }

        return result;
    }

    @GetMapping("/api/workreason/{workno}")
    public Work setReason(@PathVariable("workno") String workno){
        Work work = workRepository.findByWorkno(Integer.parseInt(workno));

        System.out.println(workno + "번의 근태 정보 확인!");

        return work;

    }
}
