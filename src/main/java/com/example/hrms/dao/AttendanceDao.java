package com.example.hrms.dao;

import com.example.hrms.model.Attendance;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDao {
    private final ConnectionFactory connectionFactory;

    public AttendanceDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public List<Attendance> listByMonth(int year, int month) throws SQLException {
        String sql = "SELECT * FROM attendance WHERE YEAR(workDate)=? AND MONTH(workDate)=? ORDER BY workDate DESC";
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, year);
            ps.setInt(2, month);
            try (ResultSet rs = ps.executeQuery()) {
                List<Attendance> result = new ArrayList<>();
                while (rs.next()) result.add(map(rs));
                return result;
            }
        }
    }

    public void upsert(Attendance a) throws SQLException {
        String sql = "MERGE INTO attendance KEY(id) VALUES(?,?,?,?)";
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            if (a.getId() == null) ps.setNull(1, Types.BIGINT); else ps.setLong(1, a.getId());
            ps.setLong(2, a.getEmployeeId());
            ps.setDate(3, Date.valueOf(a.getWorkDate()));
            ps.setString(4, a.getStatus());
            ps.executeUpdate();
        }
    }

    private Attendance map(ResultSet rs) throws SQLException {
        Attendance a = new Attendance();
        a.setId(rs.getLong("id"));
        a.setEmployeeId(rs.getLong("employee_id"));
        a.setWorkDate(rs.getDate("workDate").toLocalDate());
        a.setStatus(rs.getString("status"));
        return a;
    }
}
