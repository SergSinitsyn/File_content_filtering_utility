package statistics;

public class StringsStatistics extends Statistics {
    private int shortest = Integer.MAX_VALUE;
    private int longest = 0;

    public StringsStatistics(StatisticsType statisticsType) {
        super(statisticsType);
    }

    @Override
    public void addData(String data) {
        count++;
        if (StatisticsType.SHORT == statisticsType) {
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
        String result = "Strings statistics:" + System.lineSeparator();
        if (statisticsType == StatisticsType.SHORT || count == 0) {
            return result + "\tcount: " + count;
        }
        return result +
                "\tcount: " + count + System.lineSeparator() +
                "\tshortest: " + shortest + System.lineSeparator() +
                "\tlongest: " + longest;
    }
}