package com.carwash.daotests;

import com.carwash.dao.MembershipDAO;
import com.carwash.db.DBConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class MembershipDAOTest {

    @Test
    void testMembershipQueries() {
        assertDoesNotThrow(() -> {
            try (Connection conn = DBConnection.getConnection()) {
                assertNotNull(conn);
            }

            MembershipDAO dao = new MembershipDAO();

            dao.findActiveByCustomer(999);
        });
    }
}
