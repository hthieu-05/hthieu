package com.example.hrms.web;

import com.example.hrms.dao.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Bootstrap implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        String url = ctx.getInitParameter("jdbc.url");
        String user = ctx.getInitParameter("jdbc.user");
        String password = ctx.getInitParameter("jdbc.password");

        ConnectionFactory cf = new ConnectionFactory(url, user, password);
        ctx.setAttribute("cf", cf);
        ctx.setAttribute("employeeDao", new EmployeeDao(cf));
        ctx.setAttribute("gradeDao", new GradeDao(cf));
        ctx.setAttribute("attendanceDao", new AttendanceDao(cf));
        ctx.setAttribute("payrollDao", new PayrollDao(cf));
        ctx.setAttribute("disciplineDao", new DisciplineDao(cf));
    }
}
