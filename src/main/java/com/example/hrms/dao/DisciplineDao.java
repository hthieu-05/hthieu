package com.example.hrms.dao;

import com.example.hrms.model.Discipline;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DisciplineDao {
    private final ConnectionFactory connectionFactory;

    public DisciplineDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public List<Discipline> listByEmployee(long employeeId) throws SQLException {
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM discipline WHERE employee_id=? ORDER BY entryDate DESC")) {
            ps.setLong(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                List<Discipline> result = new ArrayList<>();
                while (rs.next()) result.add(map(rs));
                return result;
            }
        }
    }

    public long insert(Discipline d) throws SQLException {
        String sql = "INSERT INTO discipline(employee_id, type, note, entryDate) VALUES(?,?,?,?)";
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, d.getEmployeeId());
            ps.setString(2, d.getType());
            ps.setString(3, d.getNote());
            ps.setDate(4, Date.valueOf(d.getEntryDate()));
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getLong(1);
                return -1;
            }
        }
    }

    private Discipline map(ResultSet rs) throws SQLException {
        Discipline d = new Discipline();
        d.setId(rs.getLong("id"));
        d.setEmployeeId(rs.getLong("employee_id"));
        d.setType(rs.getString("type"));
        d.setNote(rs.getString("note"));
        d.setEntryDate(rs.getDate("entryDate").toLocalDate());
        return d;
    }
}
