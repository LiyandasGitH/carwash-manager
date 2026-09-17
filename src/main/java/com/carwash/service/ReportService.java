package com.carwash.service;

import com.carwash.dao.PaymentDAO;

import java.math.BigDecimal;
import java.nio.file.LinkOption;
import java.sql.SQLException;
import java.time.LocalDate;

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


    
}
