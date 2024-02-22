package Statistics;

import java.util.ArrayList;
import java.util.List;

public abstract class Statistics {

    protected StatisticsType statisticsType;
    protected List<String> data = new ArrayList<>();
    protected int count;

    public Statistics(StatisticsType statisticsType) {
        this.statisticsType = statisticsType;
    }

    public abstract void add(String data);

    protected void addData(String data) {
        this.data.add(data);
        count++;
    }

    public int getCount() {
        return count;
    }

    public List<String> getData() {
        return data;
    }
}