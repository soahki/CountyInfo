package se.soahki.countyinfo.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.soahki.countyinfo.model.Municipality;
import se.soahki.countyinfo.service.MunicipalityService;

@Controller
public class MunicipalityController {

    @Autowired
    private MunicipalityService municipalityService;

    @RequestMapping("municipalities/{municipalityId}")
    public String municipality(@PathVariable Long municipalityId, Model model) {
        Municipality municipality = municipalityService.findById(municipalityId);

        model.addAttribute("municipality", municipality);
        return "municipality/details";
    }
}
