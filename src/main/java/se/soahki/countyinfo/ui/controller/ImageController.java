package se.soahki.countyinfo.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.soahki.countyinfo.model.County;
import se.soahki.countyinfo.model.Municipality;
import se.soahki.countyinfo.model.Population;
import se.soahki.countyinfo.service.CountyService;
import se.soahki.countyinfo.service.PopulationService;
import se.soahki.countyinfo.utilities.ColorChanger;
import se.soahki.countyinfo.utilities.ImageRenderer;
import se.soahki.countyinfo.utilities.statistics.HeatMap;
import se.soahki.countyinfo.utilities.statistics.Temperature;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ImageController {

    @Autowired
    CountyService countyService;

    @Autowired
    PopulationService populationService;

    @RequestMapping("/maps/sweden.png")
    @ResponseBody
    public byte[] swedenMap() throws IOException {
        String path = "./src/main/resources/static/map/Sweden_exempel.png";
        File fileSweden = new File(path);
        BufferedImage image = ImageIO.read(fileSweden);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) image, "png", baos);

        return baos.toByteArray();
    }

    @RequestMapping("/maps/heatmap.png")
    @ResponseBody
    public byte[] counties() throws IOException {
        List<County> counties = countyService.findAll();
        List<Population> populations = populationService.findByYear(2015);
        Map<County, Integer> countyValues = new HashMap<>();
        for (County county : counties) {
            int value = 0;
            for (Population population : populations) {
                Municipality popMunicipality = population.getMunicipality();
                County popCounty = popMunicipality.getCounty();
                if (popCounty.getCode().equals(county.getCode())) {
                    value += population.getInhabitants();
                }

            }
            countyValues.put(county, value);
        }
        return HeatMap.heatCounties(countyValues);
    }

    @RequestMapping("/maps/{countyId}.png")
    @ResponseBody
    public byte[] county(@PathVariable String countyId) throws IOException {
        String path = "./src/main/resources/static/map/Sweden_exempel.png";
        File fileSweden = new File(path);
        BufferedImage image = ImageIO.read(fileSweden);

        String pathtwo = "./src/main/resources/static/map/regions/" + countyId + ".png";
        File fileCounty = new File(pathtwo);
        BufferedImage imageForeground = ImageIO.read(fileCounty);

        BufferedImage changedImage = ColorChanger.colorImageAwt(imageForeground, Color.WHITE, Color.CYAN);
        Image renderedImage = ImageRenderer.renderImageOnImage(image, changedImage);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) renderedImage, "png", baos);
        return baos.toByteArray();
    }
}
