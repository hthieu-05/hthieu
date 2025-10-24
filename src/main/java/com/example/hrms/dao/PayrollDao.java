package com.example.hrms.dao;

import com.example.hrms.model.Payroll;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollDao {
    private final ConnectionFactory connectionFactory;

    public PayrollDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public List<Payroll> list(int year, int month) throws SQLException {
        String sql = "SELECT * FROM payroll WHERE year=? AND month=? ORDER BY employee_id";
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, year);
            ps.setInt(2, month);
            try (ResultSet rs = ps.executeQuery()) {
                List<Payroll> result = new ArrayList<>();
                while (rs.next()) result.add(map(rs));
                return result;
            }
        }
    }

    public void upsert(Payroll p) throws SQLException {
        String sql = "MERGE INTO payroll KEY(employee_id,month,year) (employee_id, month, year, baseSalary, bonus, deduction, netSalary) VALUES(?,?,?,?,?,?,?)";
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, p.getEmployeeId());
            ps.setInt(2, p.getMonth());
            ps.setInt(3, p.getYear());
            ps.setBigDecimal(4, p.getBaseSalary());
            ps.setBigDecimal(5, p.getBonus());
            ps.setBigDecimal(6, p.getDeduction());
            ps.setBigDecimal(7, p.getNetSalary());
            ps.executeUpdate();
        }
    }

    private Payroll map(ResultSet rs) throws SQLException {
        Payroll p = new Payroll();
        p.setId(rs.getLong("id"));
        p.setEmployeeId(rs.getLong("employee_id"));
        p.setMonth(rs.getInt("month"));
        p.setYear(rs.getInt("year"));
        p.setBaseSalary(rs.getBigDecimal("baseSalary"));
        p.setBonus(rs.getBigDecimal("bonus"));
        p.setDeduction(rs.getBigDecimal("deduction"));
        p.setNetSalary(rs.getBigDecimal("netSalary"));
        return p;
    }
}
