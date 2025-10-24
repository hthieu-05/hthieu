package com.example.hrms.web;

import com.example.hrms.dao.EmployeeDao;
import com.example.hrms.model.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/employees", "/employees/save", "/employees/delete"})
public class EmployeeServlet extends HttpServlet {

    private EmployeeDao employeeDao;

    @Override
    public void init() throws ServletException {
        this.employeeDao = (EmployeeDao) getServletContext().getAttribute("employeeDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        try {
            switch (path) {
                case "/employees":
                    List<Employee> employees = employeeDao.findAll();
                    req.setAttribute("employees", employees);
                    req.getRequestDispatcher("/WEB-INF/views/employee/list.jsp").forward(req, resp);
                    break;
                case "/employees/delete":
                    long id = Long.parseLong(req.getParameter("id"));
                    employeeDao.delete(id);
                    resp.sendRedirect(req.getContextPath() + "/employees");
                    break;
                default:
                    resp.sendError(404);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if ("/employees/save".equals(path)) {
            Employee e = new Employee();
            e.setFullName(req.getParameter("fullName"));
            String dob = req.getParameter("dob");
            if (dob != null && !dob.isBlank()) e.setDob(LocalDate.parse(dob));
            e.setGender(req.getParameter("gender"));
            e.setIdCard(req.getParameter("idCard"));
            e.setAddress(req.getParameter("address"));
            e.setPhone(req.getParameter("phone"));
            e.setEmail(req.getParameter("email"));
            e.setSpecialty(req.getParameter("specialty"));
            e.setEducation(req.getParameter("education"));
            String pol = req.getParameter("politicalOrgDate");
            if (pol != null && !pol.isBlank()) e.setPoliticalOrgDate(LocalDate.parse(pol));
            String hire = req.getParameter("hireDate");
            if (hire != null && !hire.isBlank()) e.setHireDate(LocalDate.parse(hire));
            String gradeId = req.getParameter("gradeId");
            if (gradeId != null && !gradeId.isBlank()) e.setGradeId(Long.parseLong(gradeId));

            try {
                String idStr = req.getParameter("id");
                if (idStr == null || idStr.isBlank()) {
                    employeeDao.insert(e);
                } else {
                    e.setId(Long.parseLong(idStr));
                    employeeDao.update(e);
                }
                resp.sendRedirect(req.getContextPath() + "/employees");
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        } else {
            resp.sendError(404);
        }
    }
}
