package com.example.hrms.model;

import java.math.BigDecimal;

public class Payroll {
    private Long id;
    private Long employeeId;
    private int month;
    private int year;
    private BigDecimal baseSalary;
    private BigDecimal bonus;
    private BigDecimal deduction;
    private BigDecimal netSalary;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public BigDecimal getBaseSalary() { return baseSalary; }
    public void setBaseSalary(BigDecimal baseSalary) { this.baseSalary = baseSalary; }
    public BigDecimal getBonus() { return bonus; }
    public void setBonus(BigDecimal bonus) { this.bonus = bonus; }
    public BigDecimal getDeduction() { return deduction; }
    public void setDeduction(BigDecimal deduction) { this.deduction = deduction; }
    public BigDecimal getNetSalary() { return netSalary; }
    public void setNetSalary(BigDecimal netSalary) { this.netSalary = netSalary; }
}
