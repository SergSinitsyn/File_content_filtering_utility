package Statistics;

public class IntegerNumbersStatistics extends Statistics {
    private long max = 0L;
    private long min = 0L;
    private long sum = 0L;

    public IntegerNumbersStatistics(StatisticsType statisticsType) {
        super(statisticsType);
    }

    @Override
    public void add(String data) {
        addData(data);

        if (statisticsType == StatisticsType.SHORT) {
            return;
        }
        long number = Long.parseLong(data);
        sum += number;

        if (count == 1) {
            max = number;
            min = number;
            return;
        }
        if (number > max) {
            max = number;
            return;
        }
        if (number < min) {
            min = number;
        }
    }

    private double getAverage() {
        if (count == 0) {
            return 0;
        }
        return (double) sum / count;
    }

    public String toString() {
        if (statisticsType == StatisticsType.SHORT) {
            return "\tcount: " + count;
        }
        return "\tcount: " + count + System.lineSeparator()
                + "\tmin: " + min + System.lineSeparator()
                + "\tmax: " + max + System.lineSeparator()
                + "\tsum: " + sum + System.lineSeparator()
                + "\taverage: " + getAverage();
    }
}
