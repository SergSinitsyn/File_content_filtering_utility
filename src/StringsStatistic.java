public class StringsStatistic {

    StatisticType statisticType;
    int count = 0;
    int shortest = 0;
    int longest = 0;

    StringsStatistic(StatisticType statisticType) {
        this.statisticType = statisticType;
    }

    public int getCount() {
        return count;
    }

    public int getShortest() {
        return shortest;
    }

    public int getLongest() {
        return longest;
    }

    public void add(String str) {
        count++;
        if (StatisticType.SHORT == statisticType) {
            return;
        }
        if (count == 1) {
            shortest = str.length();
            longest = str.length();
        }
        if (str.length() < shortest) {
            shortest = str.length();
        }
        if (str.length() > longest) {
            longest = str.length();
        }
    }

    public String toString() {
        if (statisticType == StatisticType.SHORT) {
            return "\tcount: " + count;
        }
        return "\tcount: " + count + System.lineSeparator()
                + "\tshortest: " + shortest + System.lineSeparator()
                + "\tlongest: " + longest;
    }

}
