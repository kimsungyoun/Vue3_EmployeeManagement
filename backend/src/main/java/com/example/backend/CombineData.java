package com.example.backend;

import com.example.backend.entity.Work;
import com.example.backend.entity.Employee;
import com.example.backend.entity.LeaveManagement;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CombineData {
    Employee employee;
    LeaveManagement leaveManagement;
    Work attendance;

    public CombineData(Employee employee, LeaveManagement leaveManagement) {
        this.employee = employee;
        this.leaveManagement = leaveManagement;
    }

    public CombineData(Employee employee, Work attendance) {
        this.employee = employee;
        this.attendance = attendance;
    }
}
