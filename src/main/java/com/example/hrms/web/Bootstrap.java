package com.example.hrms.web;

import com.example.hrms.dao.ConnectionFactory;
import com.example.hrms.dao.EmployeeDao;
import com.example.hrms.dao.GradeDao;
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
    }
}
