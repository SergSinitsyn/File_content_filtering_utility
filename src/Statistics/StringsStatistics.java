package Statistics;

public class StringsStatistics extends Statistics {
    private int shortest;
    private int longest;

    public StringsStatistics(StatisticsType statisticsType) {
        super(statisticsType);
    }

    @Override
    public void add(String data) {
        addData(data);

        if (StatisticsType.SHORT == statisticsType) {
            return;
        }
        if (count == 1) {
            shortest = data.length();
            longest = data.length();
            return;
        }
        if (data.length() < shortest) {
            shortest = data.length();
        }
        if (data.length() > longest) {
            longest = data.length();
        }
    }

    public String toString() {
        if (statisticsType == StatisticsType.SHORT) {
            return "\tcount: " + count;
        }
        return "\tcount: " + count + System.lineSeparator()
                + "\tshortest: " + shortest + System.lineSeparator()
                + "\tlongest: " + longest;
    }
}