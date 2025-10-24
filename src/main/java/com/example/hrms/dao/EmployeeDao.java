package com.example.hrms.dao;

import com.example.hrms.model.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private final ConnectionFactory connectionFactory;

    public EmployeeDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public List<Employee> findAll() throws SQLException {
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM employee ORDER BY id DESC");
             ResultSet rs = ps.executeQuery()) {
            List<Employee> result = new ArrayList<>();
            while (rs.next()) {
                result.add(mapRow(rs));
            }
            return result;
        }
    }

    public Employee findById(long id) throws SQLException {
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM employee WHERE id=?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                return null;
            }
        }
    }

    public long insert(Employee e) throws SQLException {
        String sql = "INSERT INTO employee(fullName, dob, gender, idCard, address, phone, email, specialty, education, politicalOrgDate, hireDate, grade_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(ps, e);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getLong(1);
                return -1;
            }
        }
    }

    public void update(Employee e) throws SQLException {
        String sql = "UPDATE employee SET fullName=?, dob=?, gender=?, idCard=?, address=?, phone=?, email=?, specialty=?, education=?, politicalOrgDate=?, hireDate=?, grade_id=? WHERE id=?";
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            bind(ps, e);
            ps.setLong(13, e.getId());
            ps.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException {
        try (Connection c = connectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM employee WHERE id=?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    private void bind(PreparedStatement ps, Employee e) throws SQLException {
        ps.setString(1, e.getFullName());
        if (e.getDob() != null) ps.setDate(2, Date.valueOf(e.getDob())); else ps.setNull(2, Types.DATE);
        ps.setString(3, e.getGender());
        ps.setString(4, e.getIdCard());
        ps.setString(5, e.getAddress());
        ps.setString(6, e.getPhone());
        ps.setString(7, e.getEmail());
        ps.setString(8, e.getSpecialty());
        ps.setString(9, e.getEducation());
        if (e.getPoliticalOrgDate() != null) ps.setDate(10, Date.valueOf(e.getPoliticalOrgDate())); else ps.setNull(10, Types.DATE);
        if (e.getHireDate() != null) ps.setDate(11, Date.valueOf(e.getHireDate())); else ps.setNull(11, Types.DATE);
        if (e.getGradeId() != null) ps.setLong(12, e.getGradeId()); else ps.setNull(12, Types.BIGINT);
    }

    private Employee mapRow(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getLong("id"));
        e.setFullName(rs.getString("fullName"));
        Date d = rs.getDate("dob");
        if (d != null) e.setDob(d.toLocalDate());
        e.setGender(rs.getString("gender"));
        e.setIdCard(rs.getString("idCard"));
        e.setAddress(rs.getString("address"));
        e.setPhone(rs.getString("phone"));
        e.setEmail(rs.getString("email"));
        e.setSpecialty(rs.getString("specialty"));
        e.setEducation(rs.getString("education"));
        Date pol = rs.getDate("politicalOrgDate");
        if (pol != null) e.setPoliticalOrgDate(pol.toLocalDate());
        Date hire = rs.getDate("hireDate");
        if (hire != null) e.setHireDate(hire.toLocalDate());
        long grade = rs.getLong("grade_id");
        if (!rs.wasNull()) e.setGradeId(grade);
        return e;
    }
}
