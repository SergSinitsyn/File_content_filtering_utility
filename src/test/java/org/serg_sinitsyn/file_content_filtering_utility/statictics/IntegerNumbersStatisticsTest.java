package org.serg_sinitsyn.file_content_filtering_utility.statictics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.serg_sinitsyn.file_content_filtering_utility.statistics.IntegerNumbersStatistics;
import org.serg_sinitsyn.file_content_filtering_utility.statistics.StatisticsType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegerNumbersStatisticsTest {
    IntegerNumbersStatistics integerNumbersStatistics;

    @BeforeEach
    void setUp() {
        integerNumbersStatistics = new IntegerNumbersStatistics(StatisticsType.FULL);
    }

    @Test
    @DisplayName("Test for add data")
    public void addData() {
        integerNumbersStatistics.addData("1000");
        integerNumbersStatistics.addData("-10000");
        integerNumbersStatistics.addData("500");
        assertEquals(3, integerNumbersStatistics.getCount());
        assertEquals(1000, integerNumbersStatistics.getMax());
        assertEquals(-10000, integerNumbersStatistics.getMin());
        assertEquals(-8500, integerNumbersStatistics.getSum());
        assertEquals(-2833.3333333, integerNumbersStatistics.calculateAverage(), 1e-7);
    }

}
