package com.example.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PromoServiceTest {
    PromoService service;

    @BeforeEach
    void setUp() {
        service = new PromoService();
    }

    @Test
    public void test_TodayBetweenPromoPeriod_ReturnsTrue() {
        // Prepare
        LocalDate today = LocalDate.now();
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(1);
        service.setPromoStart(start);
        service.setPromoEnd(end);

        // Execute
        boolean result = service.checkPromoPeriod(today);

        // Assert
        assertTrue(result);
    }

    @Test
    public void test_TodayBeforePromoPeriod_ReturnsFalse() {
        // Prepare
        LocalDate today = LocalDate.now();
        LocalDate start = LocalDate.now().plusDays(1);
        LocalDate end = LocalDate.now().plusDays(2);
        service.setPromoStart(start);
        service.setPromoEnd(end);

        // Execute
        boolean result = service.checkPromoPeriod(today);

        // Assert
        assertFalse(result);
    }

    @Test
    public void test_TodayAfterPromoPeriod_ReturnsFalse() {
        // Prepare
        LocalDate today = LocalDate.now();
        LocalDate start = LocalDate.now().minusDays(2);
        LocalDate end = LocalDate.now().minusDays(1);
        service.setPromoStart(start);
        service.setPromoEnd(end);

        // Execute
        boolean result = service.checkPromoPeriod(today);

        // Assert
        assertFalse(result);
    }

    @Test
    public void test_PromoStartOrEndNull_ReturnsFalse() {
        // Prepare
        LocalDate today = LocalDate.now();
        service.setPromoStart(null);
        service.setPromoEnd(null);

        // Execute
        boolean result = service.checkPromoPeriod(today);

        // Assert
        assertFalse(result);
    }

    @Test
    public void test_TodayEqualsPromoStart_ReturnsTrue() {
        // Prepare
        LocalDate today = LocalDate.now();
        service.setPromoStart(today);
        service.setPromoEnd(today.plusDays(1));

        // Execute
        boolean result = service.checkPromoPeriod(today);

        // Assert
        assertTrue(result);
    }

    @Test
    public void test_TodayEqualsPromoEnd_ReturnsTrue() {
        // Prepare
        LocalDate today = LocalDate.now();
        service.setPromoStart(today.minusDays(1));
        service.setPromoEnd(today);

        // Execute
        boolean result = service.checkPromoPeriod(today);

        // Assert
        assertTrue(result);
    }
}
