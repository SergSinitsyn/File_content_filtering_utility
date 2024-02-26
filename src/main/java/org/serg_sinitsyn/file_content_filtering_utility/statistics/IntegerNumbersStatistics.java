package org.serg_sinitsyn.file_content_filtering_utility.statistics;

public class IntegerNumbersStatistics extends Statistics {
    private long max = Long.MIN_VALUE;
    private long min = Long.MAX_VALUE;
    private long sum = 0L;

    public IntegerNumbersStatistics(StatisticsType statisticsType) {
        super(statisticsType);
    }

    @Override
    public void addData(String data) {
        count++;
        if (statisticsType == StatisticsType.SHORT) {
            return;
        }

        long number = Long.parseLong(data);
        sum += number;
        if (number > max) {
            max = number;
        }
        if (number < min) {
            min = number;
        }
    }

    private double calculateAverage() {
        if (count == 0) {
            return 0;
        }
        return (double) sum / count;
    }

    public String toString() {
        String result = "Integer numbers statistics:" + System.lineSeparator();
        if (statisticsType == StatisticsType.SHORT || count == 0) {
            return result + "\tcount: " + count;
        }
        return result +
                "\tcount: " + count + System.lineSeparator() +
                "\tmin: " + min + System.lineSeparator() +
                "\tmax: " + max + System.lineSeparator() +
                "\tsum: " + sum + System.lineSeparator() +
                "\taverage: " + calculateAverage();
    }
}
