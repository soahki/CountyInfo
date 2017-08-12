package se.soahki.countyinfo.utilities.statistics;

import org.apache.commons.math3.stat.StatUtils;

import java.util.List;

public class Statistics {
    private double[] values;

    public Statistics(List<Integer> integerList) {
        values = new double[integerList.size()];
        convert(integerList);
    }

    public void convert(List<Integer> integers) {
        for (int i = 0; i < integers.size(); i++) {
            values[i] = integers.get(i);
        }
    }

    public double getSum() {
        return StatUtils.sum(values);
    }

    public double[] getValues() {
        return values;
    }

    public double getMin() {
        return StatUtils.min(values);
    }

    public double getMax() {
        return StatUtils.max(values);
    }

    public double getMean() {
        return StatUtils.mean(values);
    }

    public double getMedian() {
        return StatUtils.percentile(values, 50);
    }

    public double getPercentile_75() {
        return StatUtils.percentile(values, 75);
    }

    public double getStandardDeviation() {
        double[] meanDeviationSquare = new double[values.length];
        for (int i = 0; i < meanDeviationSquare.length; i++) {
            meanDeviationSquare[i] = Math.pow((values[i] - getMean()), 2);
        }
        return Math.sqrt(StatUtils.sum(meanDeviationSquare) / meanDeviationSquare.length);
    }
}
