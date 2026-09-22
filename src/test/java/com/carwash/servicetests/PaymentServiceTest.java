package com.carwash.servicetests;

import com.carwash.model.Method;
import com.carwash.model.Service;
import com.carwash.model.Ticket;
import com.carwash.service.PaymentService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTest {

    @Test
    void testProcessPaymentInvalidAmount() {
        PaymentService paymentService = new PaymentService();
        Ticket ticket = new Ticket();
        ticket.setId(1);

        // validates that zero or negative amounts are rejected before hitting the database
        IllegalStateException exZero = assertThrows(IllegalStateException.class, () -> {
            paymentService.processPayment(ticket, BigDecimal.ZERO, Method.CASH);
        });
        assertEquals("Payment amount must be greater than zero", exZero.getMessage());

        assertThrows(IllegalStateException.class, () -> {
            paymentService.processPayment(ticket, new BigDecimal("-50.00"), Method.CARD);
        });
    }

    @Test
    void testCalculatePriceIntegration() {
        PaymentService paymentService = new PaymentService();
        Service service = new Service();
        service.setPrice(new BigDecimal("100.00"));

        // hits the database via membershipDAO.findActiveByCustomer()
        assertDoesNotThrow(() -> {
            BigDecimal price = paymentService.calculatePrice(service, 999);
            assertNotNull(price, "Calculated price should not be null");
        });
    }

    @Test
    void testProcessPaymentIntegration() {
        PaymentService paymentService = new PaymentService();
        Ticket ticket = new Ticket();
        ticket.setId(999); // Dummy ID

        // hits the database via paymentDAO.insert() and ticketDAO.updateStatus()
        assertDoesNotThrow(() -> {

            PaymentService.Receipt receipt = paymentService.processPayment(ticket, new BigDecimal("100.00"), Method.CARD);

            assertNotNull(receipt, "Receipt should be successfully generated");
            assertEquals(Method.CARD, receipt.method());
            assertEquals(new BigDecimal("100.00"), receipt.amountCharged());
        });
    }
}