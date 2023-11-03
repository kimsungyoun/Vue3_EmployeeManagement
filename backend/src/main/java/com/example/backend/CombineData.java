package com.example.backend;

import com.example.backend.entity.Attendance;
import com.example.backend.entity.Employee;
import com.example.backend.entity.LeaveManagement;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CombineData {
    Employee employee;
    LeaveManagement leaveManagement;
    Attendance attendance;

    public CombineData(Employee employee, LeaveManagement leaveManagement) {
        this.employee = employee;
        this.leaveManagement = leaveManagement;
    }

    public CombineData(Employee employee, Attendance attendance) {
        this.employee = employee;
        this.attendance = attendance;
    }
}
