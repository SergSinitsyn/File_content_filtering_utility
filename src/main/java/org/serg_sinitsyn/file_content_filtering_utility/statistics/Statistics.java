package org.serg_sinitsyn.file_content_filtering_utility.statistics;

public abstract class Statistics {

    protected StatisticsType statisticsType;
    protected int count;

    public Statistics(StatisticsType statisticsType) {
        this.statisticsType = statisticsType;
    }

    public abstract void addData(String data);

    public int getCount() {
        return count;
    }
}

