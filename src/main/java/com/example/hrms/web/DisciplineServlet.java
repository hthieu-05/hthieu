package com.example.hrms.web;

import com.example.hrms.dao.DisciplineDao;
import com.example.hrms.model.Discipline;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = {"/discipline", "/discipline/save"})
public class DisciplineServlet extends HttpServlet {
    private DisciplineDao disciplineDao;

    @Override
    public void init() throws ServletException {
        this.disciplineDao = (DisciplineDao) getServletContext().getAttribute("disciplineDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long employeeId = parseLong(req.getParameter("employeeId"), -1);
        if (employeeId <= 0) { resp.sendError(400, "employeeId required"); return; }
        try {
            List<Discipline> list = disciplineDao.listByEmployee(employeeId);
            req.setAttribute("list", list);
            req.setAttribute("employeeId", employeeId);
            req.getRequestDispatcher("/WEB-INF/views/discipline/list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Discipline d = new Discipline();
        d.setEmployeeId(Long.parseLong(req.getParameter("employeeId")));
        d.setType(req.getParameter("type"));
        d.setNote(req.getParameter("note"));
        d.setEntryDate(LocalDate.parse(req.getParameter("entryDate")));
        try {
            disciplineDao.insert(d);
            resp.sendRedirect(req.getContextPath() + "/discipline?employeeId=" + d.getEmployeeId());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private long parseLong(String s, long def) {
        try { return Long.parseLong(s); } catch (Exception e) { return def; }
    }
}
