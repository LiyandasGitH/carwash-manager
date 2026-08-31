package com.carwash.modeltests;

import com.carwash.model.Method;
import com.carwash.model.Payment;
import com.carwash.model.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {

    @Test
    void testGettersAndSetters() {
        Payment payment = new Payment();
        LocalDateTime paidTime = LocalDateTime.now();

        payment.setId(1);
        payment.setTicketId(100);
        payment.setAmount(new BigDecimal("150.00"));
        payment.setMethod(Method.CASH);
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setPaid(paidTime);

        assertEquals(1, payment.getId());
        assertEquals(100, payment.getTicketId());
        assertEquals(new BigDecimal("150.00"), payment.getAmount());
        assertEquals(Method.CASH, payment.getMethod());
        assertEquals(PaymentStatus.COMPLETED, payment.getStatus());
        assertEquals(paidTime, payment.getPaid());
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime paidTime = LocalDateTime.now();
        Payment payment = new Payment(2, 101, new BigDecimal("250.50"), Method.CARD, PaymentStatus.PENDING, paidTime);

        assertEquals(2, payment.getId());
        assertEquals(101, payment.getTicketId());
        assertEquals(new BigDecimal("250.50"), payment.getAmount());
        assertEquals(Method.CARD, payment.getMethod());
        assertEquals(PaymentStatus.PENDING, payment.getStatus());
        assertEquals(paidTime, payment.getPaid());
    }
}
