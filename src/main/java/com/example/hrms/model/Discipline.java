package com.example.hrms.model;

import java.time.LocalDate;

public class Discipline {
    private Long id;
    private Long employeeId;
    private String type; // Reward or Punish
    private String note;
    private LocalDate entryDate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public LocalDate getEntryDate() { return entryDate; }
    public void setEntryDate(LocalDate entryDate) { this.entryDate = entryDate; }
}
