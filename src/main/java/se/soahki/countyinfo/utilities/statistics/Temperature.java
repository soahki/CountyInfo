package se.soahki.countyinfo.utilities.statistics;


import java.awt.*;

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
        increment = (255+255+255)/(difference + 1);
    }

    public Color heatOfValue(double value) {
        double temperature = value * increment;
        int red = 0;
        int green = 0;
        int blue = 0;

        blue = (int) temperature;
        if (temperature > 255) {
            blue = 255;
            temperature -= 255;
            green = (int) temperature;
            if (temperature > 255) {
                temperature -= 255;
                red = (int) temperature;
                blue = 255 - red;
                green = 255 - red;
            }
        }
        if (red > 255) {
            red = 255;
        }if (green > 255) {
            green = 255;
        }if (blue > 255) {
            blue = 255;
        }

        return new Color(red, green, blue, 255);
    }
}
