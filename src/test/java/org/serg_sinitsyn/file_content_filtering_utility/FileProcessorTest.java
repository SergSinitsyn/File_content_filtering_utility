package org.serg_sinitsyn.file_content_filtering_utility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.serg_sinitsyn.file_content_filtering_utility.statistics.StatisticsType;

import static org.junit.jupiter.api.Assertions.*;


class FileProcessorTest {
    FileProcessor fileProcessor;

    @BeforeEach
    void setUp() {
        fileProcessor = new FileProcessor(
                StatisticsType.FULL, false, "", "");
    }

    @ParameterizedTest
    @DisplayName("Test for integer numbers")
    @ValueSource(strings = {"445", "1234567890123456789", "100500"})
    public void identifyDataType_integerNumber(String string) {
        assertEquals(DataType.INTEGER_NUMBER, fileProcessor.identifyDataType(string));
    }

    @ParameterizedTest
    @DisplayName("Test for real numbers")
    @ValueSource(strings = {"1.528535047E-25", "3.1415", "-0.001",
            "25e3", "-3.23", "5.35235", "7.34e+13", "11.243E-1234",
            "-000.123"})
    public void identifyDataType_realNumber(String string) {
        assertEquals(DataType.REAL_NUMBER, fileProcessor.identifyDataType(string));
    }

    @ParameterizedTest
    @DisplayName("Test for strings")
    @ValueSource(strings = {"Lorem ipsum dolor sit amet", "Пример", "consectetur adipiscing",
            "тестовое задание", "Нормальная форма числа с плавающей запятой",
            "Long"})
    public void identifyDataTypeTest_string(String string) {
        assertEquals(DataType.STRING, fileProcessor.identifyDataType(string));
    }


//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void processFile() {
//    }
//
//    @Test
//    void printStatistics() {
//    }
//
//    @Test
//    void closeFiles() {
//    }
//
//    @Test
//    void identifyDataType() {
//    }
}
