package org.serg_sinitsyn.file_content_filtering_utility.statictics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.serg_sinitsyn.file_content_filtering_utility.statistics.RealNumbersStatistics;
import org.serg_sinitsyn.file_content_filtering_utility.statistics.Statistics;
import org.serg_sinitsyn.file_content_filtering_utility.statistics.StatisticsType;
import org.serg_sinitsyn.file_content_filtering_utility.statistics.StringsStatistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringsStatisticsTest {
    StringsStatistics stringsStatistics;

    @BeforeEach
    void setUp() {
        stringsStatistics = new StringsStatistics(StatisticsType.FULL);
    }

    @Test
    @DisplayName("Test for add data")
    public void addData() {
        stringsStatistics.addData("Lorem ipsum dolor sit amet");
        stringsStatistics.addData("Нормальная форма числа с плавающей запятой");
        stringsStatistics.addData("Пример");
        stringsStatistics.addData("Long");
        stringsStatistics.addData("consectetur adipiscing");
        stringsStatistics.addData("тестовое задание");
        assertEquals(6, stringsStatistics.getCount());
        assertEquals(42, stringsStatistics.getLongest());
        assertEquals(4, stringsStatistics.getShortest());
    }

}