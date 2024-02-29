package org.serg_sinitsyn.file_content_filtering_utility.statictics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.serg_sinitsyn.file_content_filtering_utility.statistics.RealNumbersStatistics;
import org.serg_sinitsyn.file_content_filtering_utility.statistics.StatisticsType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RealNumbersStatisticsTest {

    RealNumbersStatistics realNumbersStatistics;

    @BeforeEach
    void setUp() {
        realNumbersStatistics = new RealNumbersStatistics(StatisticsType.FULL);
    }

    @Test
    @DisplayName("Test for add data")
    public void addData() {
        realNumbersStatistics.addData("1.528535047E-25");
        realNumbersStatistics.addData("3.1415");
        realNumbersStatistics.addData("-0.001");
        assertEquals(3, realNumbersStatistics.getCount());
        assertEquals(3.1415, realNumbersStatistics.getMax());
        assertEquals(-0.001, realNumbersStatistics.getMin());
        assertEquals(3.1405, realNumbersStatistics.getSum(), 1e-7);
        assertEquals(1.04683333, realNumbersStatistics.calculateAverage(), 1e-7);
    }
}
