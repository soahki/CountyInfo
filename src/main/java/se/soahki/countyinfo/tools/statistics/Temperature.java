package se.soahki.countyinfo.tools.statistics;

import javafx.scene.paint.Color;

public class Temperature {
    private double min;
    private double max;
    private double increment;


    public Temperature(double min, double max) {
        this.min = min;
        this.max = max;
        normalizer();
    }

    private void normalizer() {
        double difference = Math.abs(max - min);
        increment = difference / (255+255+255);
    }

    public Color heatOfValue(double value) {
        double temperature = value * increment;
        double red = 0;
        double green = 0;
        double blue = 0;
        if (temperature < 255) {
            blue = (int) temperature;
        } else if (temperature < (255+255)) {
            green = (int) (temperature - 255);
        } else {
            red = (int) (temperature - (255 + 255));
        }
        return new Color(red, green, blue, 1);
    }
}
