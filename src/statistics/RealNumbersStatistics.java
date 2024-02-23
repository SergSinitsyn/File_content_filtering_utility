package statistics;

public class RealNumbersStatistics extends Statistics {
    private double max = Double.MIN_VALUE;
    private double min = Double.MAX_VALUE;
    private double sum = 0;

    public RealNumbersStatistics(StatisticsType statisticsType) {
        super(statisticsType);
    }

    @Override
    public void addData(String data) {
        count++;
        if (statisticsType == StatisticsType.SHORT) {
            return;
        }


        double number = Double.parseDouble(data);
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
        return sum / count;
    }

    public String toString() {
        if (statisticsType == StatisticsType.SHORT || count == 0) {
            return "\tcount: " + count;
        }
        return "\tcount: " + count + System.lineSeparator()
                + "\tmin: " + min + System.lineSeparator()
                + "\tmax: " + max + System.lineSeparator()
                + "\tsum: " + sum + System.lineSeparator()
                + "\taverage: " + calculateAverage();
    }
}
