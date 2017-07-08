package se.soahki.countyinfo.utilities.statistics;

import se.soahki.countyinfo.model.County;
import se.soahki.countyinfo.service.CountyService;
import se.soahki.countyinfo.utilities.ColorChanger;
import se.soahki.countyinfo.utilities.ImageRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class HeatMap {

    public static byte[] heatCounties(List<County> counties) throws IOException {
        String pathSweden = "./src/main/resources/static/map/Sweden_exempel.png";
        File fileSweden = new File(pathSweden);
        BufferedImage imageSweden = ImageIO.read(fileSweden);

        BufferedImage compiledImage = imageSweden;
        Temperature temp = new Temperature(1, 21);
        int i = 1;
        for (County county : counties) {
            String pathCounty = "./src/main/resources/static/map/regions/" + county.getCode() + ".png";
            File fileCounty = new File(pathCounty);
            BufferedImage image = ImageIO.read(fileCounty);
            image = ColorChanger.colorImageAwt(image, Color.WHITE, temp.heatOfValue(i));
            compiledImage = ImageRenderer.renderImageOnImage(compiledImage, image);
            i++;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) compiledImage, "png", baos);
        byte[] res = baos.toByteArray();
        return res;
    }

    public static byte[] heatCounties(List<County> counties, List<Double> values) throws IOException {
        String pathSweden = "./src/main/resources/static/map/Sweden_exempel.png";
        File fileSweden = new File(pathSweden);
        BufferedImage imageSweden = ImageIO.read(fileSweden);

        BufferedImage compiledImage = imageSweden;
        Temperature temp = new Temperature(1, 21);
        int i = 1;
        for (County county : counties) {
            String pathCounty = "./src/main/resources/static/map/regions/" + county.getCode() + ".png";
            File fileCounty = new File(pathCounty);
            BufferedImage image = ImageIO.read(fileCounty);
            image = ColorChanger.colorImageAwt(image, Color.WHITE, temp.heatOfValue(i));
            compiledImage = ImageRenderer.renderImageOnImage(compiledImage, image);
            i++;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) compiledImage, "png", baos);
        byte[] res = baos.toByteArray();
        return res;
    }


}
