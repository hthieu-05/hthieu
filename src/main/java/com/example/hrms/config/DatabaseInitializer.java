package com.example.hrms.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String url = sce.getServletContext().getInitParameter("jdbc.url");
        String user = sce.getServletContext().getInitParameter("jdbc.user");
        String password = sce.getServletContext().getInitParameter("jdbc.password");

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (Statement st = connection.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS grade (" +
                        "id IDENTITY PRIMARY KEY, " +
                        "name VARCHAR(50) NOT NULL, " +
                        "minSalary DECIMAL(15,2) NOT NULL, " +
                        "maxSalary DECIMAL(15,2) NOT NULL");

                st.execute("CREATE TABLE IF NOT EXISTS employee (" +
                        "id IDENTITY PRIMARY KEY, " +
                        "fullName VARCHAR(120) NOT NULL, " +
                        "dob DATE, " +
                        "gender VARCHAR(10), " +
                        "idCard VARCHAR(20), " +
                        "address VARCHAR(255), " +
                        "phone VARCHAR(30), " +
                        "email VARCHAR(120), " +
                        "specialty VARCHAR(100), " +
                        "education VARCHAR(100), " +
                        "politicalOrgDate DATE, " +
                        "hireDate DATE, " +
                        "grade_id BIGINT, " +
                        "FOREIGN KEY (grade_id) REFERENCES grade(id)");

                st.execute("CREATE TABLE IF NOT EXISTS attendance (" +
                        "id IDENTITY PRIMARY KEY, " +
                        "employee_id BIGINT NOT NULL, " +
                        "workDate DATE NOT NULL, " +
                        "status VARCHAR(20) NOT NULL, " +
                        "FOREIGN KEY (employee_id) REFERENCES employee(id)");

                st.execute("CREATE TABLE IF NOT EXISTS payroll (" +
                        "id IDENTITY PRIMARY KEY, " +
                        "employee_id BIGINT NOT NULL, " +
                        "month INT NOT NULL, " +
                        "year INT NOT NULL, " +
                        "baseSalary DECIMAL(15,2) NOT NULL, " +
                        "bonus DECIMAL(15,2) NOT NULL, " +
                        "deduction DECIMAL(15,2) NOT NULL, " +
                        "netSalary DECIMAL(15,2) NOT NULL, " +
                        "FOREIGN KEY (employee_id) REFERENCES employee(id)");

                st.execute("CREATE TABLE IF NOT EXISTS discipline (" +
                        "id IDENTITY PRIMARY KEY, " +
                        "employee_id BIGINT NOT NULL, " +
                        "type VARCHAR(30) NOT NULL, " +
                        "note VARCHAR(255), " +
                        "entryDate DATE NOT NULL, " +
                        "FOREIGN KEY (employee_id) REFERENCES employee(id)");

                st.execute("CREATE INDEX IF NOT EXISTS idx_attendance_emp_date ON attendance(employee_id, workDate)");
                st.execute("CREATE UNIQUE INDEX IF NOT EXISTS idx_payroll_emp_month ON payroll(employee_id, month, year)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
