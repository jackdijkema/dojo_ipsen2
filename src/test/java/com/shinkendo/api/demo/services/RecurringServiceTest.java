package com.shinkendo.api.demo.services;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RecurringServiceTest {

    // 1 Week difference
    @Test
    public void testCalculateWeeksBetweenDates_basicCase() {
        LocalDate currentDate = LocalDate.now();
        LocalDate oneWeekFromNow = currentDate.plusWeeks(1);

        long result = ChronoUnit.WEEKS.between(currentDate, oneWeekFromNow);

        assertEquals(1, result);
    }

    //0 Weeks in-between
    @Test
    public void testCalculateWeeksBetweenDates_zeroWeeksCase() {
        LocalDate currentDate = LocalDate.now();

        long result = ChronoUnit.WEEKS.between(currentDate, currentDate);

        assertEquals(0, result);
    }
    // Negative weeks
    @Test
    public void testCalculateWeeksBetweenDates_negativeWeeksCase() {
        LocalDate currentDate = LocalDate.now();
        LocalDate twoWeeksAgo = currentDate.minusWeeks(2);

        long result = ChronoUnit.WEEKS.between(currentDate, twoWeeksAgo);

        assertEquals(-2, result);
    }

    // > 50 Weeks difference
    @Test
    public void testCalculateWeeksBetweenDates_differentYearsCase() {
        LocalDate currentDate = LocalDate.now();
        LocalDate endOfNextYear = currentDate.plusYears(1);

        long result = ChronoUnit.WEEKS.between(currentDate, endOfNextYear);

        assertTrue(result > 50);
    }
    // A lot of weeks in-between
    @Test
    public void testCalculateWeeksBetweenDates_edgeCases() {
        LocalDate currentDate = LocalDate.now();
        LocalDate farFutureDate = LocalDate.of(2100, 1, 1);

        long result = ChronoUnit.WEEKS.between(currentDate, farFutureDate);

        assertTrue(result > 1000);
    }
}
