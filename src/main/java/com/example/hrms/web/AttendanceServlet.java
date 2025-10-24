package com.example.hrms.web;

import com.example.hrms.dao.AttendanceDao;
import com.example.hrms.model.Attendance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = {"/attendance", "/attendance/save"})
public class AttendanceServlet extends HttpServlet {
    private AttendanceDao attendanceDao;

    @Override
    public void init() throws ServletException {
        this.attendanceDao = (AttendanceDao) getServletContext().getAttribute("attendanceDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int year = parseInt(req.getParameter("year"), LocalDate.now().getYear());
        int month = parseInt(req.getParameter("month"), LocalDate.now().getMonthValue());
        try {
            List<Attendance> list = attendanceDao.listByMonth(year, month);
            req.setAttribute("list", list);
            req.setAttribute("year", year);
            req.setAttribute("month", month);
            req.getRequestDispatcher("/WEB-INF/views/attendance/list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Attendance a = new Attendance();
        a.setEmployeeId(Long.parseLong(req.getParameter("employeeId")));
        a.setWorkDate(LocalDate.parse(req.getParameter("workDate")));
        a.setStatus(req.getParameter("status"));
        try {
            attendanceDao.upsert(a);
            resp.sendRedirect(req.getContextPath() + "/attendance?year=" + a.getWorkDate().getYear() + "&month=" + a.getWorkDate().getMonthValue());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private int parseInt(String s, int def) {
        try { return Integer.parseInt(s); } catch (Exception e) { return def; }
    }
}
