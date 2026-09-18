package com.carwash.service;

import com.carwash.dao.MembershipDAO;
import com.carwash.dao.PaymentDAO;
import com.carwash.dao.TicketDAO;
import com.carwash.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

public class PaymentService {

    private final PaymentDAO paymentDAO = new PaymentDAO();
    private final TicketDAO ticketDAO = new TicketDAO();
    private final MembershipDAO membershipDAO = new MembershipDAO();

    private static final BigDecimal BASIC_MEMBER_DISCOUNT = new BigDecimal("0.10"); // 10% off
    private static final BigDecimal PREMIUM_MEMBER_DISCOUNT = new BigDecimal("0.20"); // 20% off

    public BigDecimal calculatePrice(Service service, int customerId) throws SQLException {
        BigDecimal base = service.getPrice();
        Membership membership = membershipDAO.findActiveByCustomer(customerId);
        if (membership == null) {
            return base;
        }

        BigDecimal discount = switch (membership.getPlan()) {
            case "PREMIUM" -> PREMIUM_MEMBER_DISCOUNT;
            case "BASIC" -> BASIC_MEMBER_DISCOUNT;
            default -> BigDecimal.ZERO;
        };

        BigDecimal reduction = base.multiply(discount);
        return base.subtract(reduction).setScale(2, RoundingMode.HALF_UP);
    }

    public record Receipt(int paymentId, BigDecimal amountCharged, Method method) {
    }

    public Receipt processPayment(Ticket ticket, BigDecimal amount, Method method) throws SQLException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Payment amount must be greater than zero");
        }

        boolean approved = true;
        if (!approved) {
            throw new IllegalStateException("Payment declined");
        }

        Payment payment = new Payment();
        payment.setTicketId(ticket.getId());
        payment.setAmount(amount);
        payment.setMethod(method);

        payment.setStatus(PaymentStatus.COMPLETED);

        int paymentId = paymentDAO.insert(payment);
        ticketDAO.updateStatus(ticket.getId(), TicketStatus.PAID);

        return new Receipt(paymentId, amount, method);
    }


}
