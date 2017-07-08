package se.soahki.countyinfo.utilities;


import java.awt.image.BufferedImage;

public class ImageRenderer {

    public static BufferedImage renderImageOnImage(BufferedImage imageBackground, BufferedImage imageForeground) {
        if (imageBackground.getWidth() < imageForeground.getWidth() ||
                imageBackground.getHeight() < imageForeground.getHeight()) {
            throw new UnsupportedOperationException("The background image has to be bigger than the foreground");
        }

        BufferedImage compiledImage =  new BufferedImage(imageBackground.getWidth(), imageBackground.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Render background image first
        for (int x = 0; x < compiledImage.getWidth(); x++) {
            for (int y = 0; y < compiledImage.getHeight(); y++) {
                compiledImage.setRGB(x, y, imageBackground.getRGB(x, y));
            }
        }

        // Render second image upon the first
        for (int x = 0; x < compiledImage.getWidth(); x++) {
            for (int y = 0; y < compiledImage.getHeight(); y++) {
                if (imageForeground.getRGB(x, y) != 0) {
                    compiledImage.setRGB(x, y, imageForeground.getRGB(x, y));
                }
            }
        }



        return compiledImage;
    }

}
