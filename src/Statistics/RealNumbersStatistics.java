package Statistics;

public class RealNumbersStatistics extends Statistics {
    private double max = 0;
    private double min = 0;
    private double sum = 0;

    public RealNumbersStatistics(StatisticsType statisticsType) {
        super(statisticsType);
    }

    @Override
    public void add(String data) {
        addData(data);

        if (statisticsType == StatisticsType.SHORT) {
            return;
        }
        double number = Double.parseDouble(data);
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
        return sum / count;
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
