package com.example.hrms.web;

import com.example.hrms.dao.PayrollDao;
import com.example.hrms.model.Payroll;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = {"/payroll", "/payroll/save"})
public class PayrollServlet extends HttpServlet {
    private PayrollDao payrollDao;

    @Override
    public void init() throws ServletException {
        this.payrollDao = (PayrollDao) getServletContext().getAttribute("payrollDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int year = parseInt(req.getParameter("year"), LocalDate.now().getYear());
        int month = parseInt(req.getParameter("month"), LocalDate.now().getMonthValue());
        try {
            List<Payroll> list = payrollDao.list(year, month);
            req.setAttribute("list", list);
            req.setAttribute("year", year);
            req.setAttribute("month", month);
            req.getRequestDispatcher("/WEB-INF/views/payroll/list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Payroll p = new Payroll();
        p.setEmployeeId(Long.parseLong(req.getParameter("employeeId")));
        p.setYear(Integer.parseInt(req.getParameter("year")));
        p.setMonth(Integer.parseInt(req.getParameter("month")));
        p.setBaseSalary(new BigDecimal(req.getParameter("baseSalary")));
        p.setBonus(new BigDecimal(req.getParameter("bonus")));
        p.setDeduction(new BigDecimal(req.getParameter("deduction")));
        p.setNetSalary(p.getBaseSalary().add(p.getBonus()).subtract(p.getDeduction()));
        try {
            payrollDao.upsert(p);
            resp.sendRedirect(req.getContextPath() + "/payroll?year=" + p.getYear() + "&month=" + p.getMonth());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private int parseInt(String s, int def) {
        try { return Integer.parseInt(s); } catch (Exception e) { return def; }
    }
}
