package se.soahki.countyinfo.utilities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorChanger {
    public static BufferedImage colorImageAwt(BufferedImage image, Color originalColor, Color newColor) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(image.getRGB(x,y) == originalColor.getRGB()) {
                    image.setRGB(x, y, newColor.getRGB());
                }
            }
        }

        return image;
    }
}
