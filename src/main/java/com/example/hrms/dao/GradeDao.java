package com.example.hrms.dao;

import com.example.hrms.model.Grade;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDao {
    private final ConnectionFactory connectionFactory;

    public GradeDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public List<Grade> findAll() throws SQLException {
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM grade ORDER BY minSalary ASC");
             ResultSet rs = ps.executeQuery()) {
            List<Grade> result = new ArrayList<>();
            while (rs.next()) {
                result.add(mapRow(rs));
            }
            return result;
        }
    }

    public Grade findById(long id) throws SQLException {
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM grade WHERE id=?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                return null;
            }
        }
    }

    public long insert(Grade g) throws SQLException {
        String sql = "INSERT INTO grade(name, minSalary, maxSalary) VALUES(?,?,?)";
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, g.getName());
            ps.setBigDecimal(2, g.getMinSalary());
            ps.setBigDecimal(3, g.getMaxSalary());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getLong(1);
                return -1;
            }
        }
    }

    public void update(Grade g) throws SQLException {
        String sql = "UPDATE grade SET name=?, minSalary=?, maxSalary=? WHERE id=?";
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, g.getName());
            ps.setBigDecimal(2, g.getMinSalary());
            ps.setBigDecimal(3, g.getMaxSalary());
            ps.setLong(4, g.getId());
            ps.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException {
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM grade WHERE id=?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    private Grade mapRow(ResultSet rs) throws SQLException {
        Grade g = new Grade();
        g.setId(rs.getLong("id"));
        g.setName(rs.getString("name"));
        g.setMinSalary(rs.getBigDecimal("minSalary"));
        g.setMaxSalary(rs.getBigDecimal("maxSalary"));
        return g;
    }
}
