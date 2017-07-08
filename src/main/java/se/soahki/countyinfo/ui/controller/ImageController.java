package se.soahki.countyinfo.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.soahki.countyinfo.model.County;
import se.soahki.countyinfo.service.CountyService;
import se.soahki.countyinfo.utilities.ColorChanger;
import se.soahki.countyinfo.utilities.ImageRenderer;
import se.soahki.countyinfo.utilities.statistics.HeatMap;
import se.soahki.countyinfo.utilities.statistics.Temperature;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


@Controller
public class ImageController {

    @Autowired
    CountyService countyService;

   // @RequestMapping("/maps/sweden.png")
   // @ResponseBody
    public byte[] swedenMap() throws IOException {
        String path = "./src/main/resources/static/map/Sweden_exempel.png";
        File fileSweden = new File(path);
        BufferedImage image = ImageIO.read(fileSweden);

        String pathtwo = "./src/main/resources/static/map/regions/AB.png";
        File fileCounty = new File(pathtwo);
        BufferedImage imageForeground = ImageIO.read(fileCounty);

        BufferedImage changedImage = ColorChanger.colorImageAwt(imageForeground, Color.WHITE, Color.CYAN);
        Image renderedImage = ImageRenderer.renderImageOnImage(image, changedImage);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) renderedImage, "png", baos);
        byte[] res = baos.toByteArray();

        //return Files.readAllBytes(file.toPath());
        return res;
    }

    @RequestMapping("/maps/sweden.png")
    @ResponseBody
    public byte[] counties() throws IOException {
        return HeatMap.heatCounties(countyService.findAll());
    }
}
