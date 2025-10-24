package com.example.hrms.web;

import com.example.hrms.dao.GradeDao;
import com.example.hrms.model.Grade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GradeServlet", urlPatterns = {"/grades", "/grades/save", "/grades/delete"})
public class GradeServlet extends HttpServlet {

    private GradeDao gradeDao;

    @Override
    public void init() throws ServletException {
        this.gradeDao = (GradeDao) getServletContext().getAttribute("gradeDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        try {
            switch (path) {
                case "/grades":
                    List<Grade> grades = gradeDao.findAll();
                    req.setAttribute("grades", grades);
                    req.getRequestDispatcher("/WEB-INF/views/grade/list.jsp").forward(req, resp);
                    break;
                case "/grades/delete":
                    long id = Long.parseLong(req.getParameter("id"));
                    gradeDao.delete(id);
                    resp.sendRedirect(req.getContextPath() + "/grades");
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
        if ("/grades/save".equals(path)) {
            Grade g = new Grade();
            g.setName(req.getParameter("name"));
            g.setMinSalary(new BigDecimal(req.getParameter("minSalary")));
            g.setMaxSalary(new BigDecimal(req.getParameter("maxSalary")));
            try {
                String idStr = req.getParameter("id");
                if (idStr == null || idStr.isBlank()) {
                    gradeDao.insert(g);
                } else {
                    g.setId(Long.parseLong(idStr));
                    gradeDao.update(g);
                }
                resp.sendRedirect(req.getContextPath() + "/grades");
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        } else {
            resp.sendError(404);
        }
    }
}
