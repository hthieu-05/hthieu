package com.example.hrms.model;

import java.time.LocalDate;

public class Attendance {
    private Long id;
    private Long employeeId;
    private LocalDate workDate;
    private String status; // Present, Absent, Leave

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public LocalDate getWorkDate() { return workDate; }
    public void setWorkDate(LocalDate workDate) { this.workDate = workDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
