package com.shinkendo.api.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class RecurringServiceTest {

    @InjectMocks
    public RecurringService recurringService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("1 Week difference")
    public void testCalculateWeeksBetweenDates_basicCase() {
        LocalDate oneWeekFromNow = LocalDate.now().plusWeeks(1);

        long result = recurringService.calculateWeeksUntil(oneWeekFromNow);

        long expected = ChronoUnit.WEEKS.between(LocalDate.now(), oneWeekFromNow);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("0 Weeks in-between")
    public void testCalculateWeeksBetweenDates_zeroWeeksCase() {
        long result = recurringService.calculateWeeksUntil(LocalDate.now());

        assertEquals(0, recurringService.calculateWeeksUntil(LocalDate.now()));
    }

    @Test
    @DisplayName("Negative weeks value")
    public void testCalculateWeeksBetweenDates_negativeWeeksCase() {
        LocalDate twoWeeksAgo = LocalDate.now().minusWeeks(2);

        long result = recurringService.calculateWeeksUntil(twoWeeksAgo);

        assertEquals(-2, recurringService.calculateWeeksUntil(twoWeeksAgo));
    }

    @Test
    @DisplayName("> 50 Weeks difference")
    public void testCalculateWeeksBetweenDates_differentYearsCase() {
        LocalDate endOfNextYear = LocalDate.now().plusYears(1);

        long result = recurringService.calculateWeeksUntil(endOfNextYear);

        assertTrue(recurringService.calculateWeeksUntil(endOfNextYear) > 50);
    }

    @Test
    @DisplayName("A ton of weeks ~3000")
    public void testCalculateWeeksBetweenDates_edgeCases() {
        LocalDate farFutureDate = LocalDate.of(2100, 1, 1);

        long result = recurringService.calculateWeeksUntil(farFutureDate);

        assertTrue(recurringService.calculateWeeksUntil(farFutureDate) > 1000);
    }
}