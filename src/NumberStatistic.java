public class NumberStatistic {
    StatisticType statisticType;
    int count = 0;
    double max = Double.MIN_VALUE;
    double min = Double.MAX_VALUE;
    double sum = 0;

    NumberStatistic(StatisticType statisticType) {
        this.statisticType = statisticType;
    }

    public int getCount() {
        return count;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    double getAverage() {
        if (count == 0) {
            return 0;
        }
        return (double) sum / count;
    }

    public void add(String string) {
        count++;
        if (StatisticType.SHORT == statisticType) {
            return;
        }

        double number = Double.parseDouble(string);
        if (number > max) {
            max = number;
            sum += number;
        }
        if (number < min) {
            min = number;
            sum += number;
        }
    }

    public String toString() {
        if (statisticType == StatisticType.SHORT) {
            return "\tcount: " + count;
        }
        return "\tcount: " + count + System.lineSeparator()
                + "\tmin: " + min + System.lineSeparator()
                + "\tmax: " + max + System.lineSeparator()
                + "\tsum: " + sum + System.lineSeparator()
                + "\taverage: " + getAverage();
    }

}
