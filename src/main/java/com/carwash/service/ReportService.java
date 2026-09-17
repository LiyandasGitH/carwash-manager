package com.carwash.service;

import com.carwash.dao.PaymentDAO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ReportService {

    private final PaymentDAO paymentDAO = new PaymentDAO();

    public BigDecimal revenueToday() throws SQLException {
        LocalDate today = LocalDate.now();
        return paymentDAO.totalRevenueBetween(today, today);
    }

    public BigDecimal revenueThisWeek() throws SQLException {
        LocalDate today = LocalDate.now();
        return paymentDAO.totalRevenueBetween(today.minusDays(6), today);
    }

    public List<Object[]> revenueByDay(int days) throws SQLException {
        return paymentDAO.revenueByDay(days);
    }

    public List<Object[]> topCustomers(int limit) throws SQLException {
        return paymentDAO.topCustomers(limit);
    }
    
}
