package com.carwash.modeltests;

import com.carwash.model.Membership;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MembershipTest {

    @Test
    void testGettersAndSetters() {
        Membership membership = new Membership();
        LocalDate renewalDate = LocalDate.of(2026, 12, 31);

        membership.setId(1);
        membership.setCustomerId(101);
        membership.setPlan("PREMIUM");
        membership.setMonthlyFee(new BigDecimal("150.00"));
        membership.setActive(true);
        membership.setRenewing(renewalDate);

        assertEquals(1, membership.getId());
        assertEquals(101, membership.getCustomerId());
        assertEquals("PREMIUM", membership.getPlan());
        assertEquals(new BigDecimal("150.00"), membership.getMonthlyFee());
        assertTrue(membership.isActive());
        assertEquals(renewalDate, membership.getRenewing());
    }
}
