package se.soahki.countyinfo.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.soahki.countyinfo.model.Municipality;
import se.soahki.countyinfo.model.Population;
import se.soahki.countyinfo.service.MunicipalityService;
import se.soahki.countyinfo.service.PopulationService;

import java.util.List;

@Controller
public class MunicipalityController {

    @Autowired
    private MunicipalityService municipalityService;

    @Autowired
    private PopulationService populationService;

    @RequestMapping("municipalities/{municipalityId}")
    public String municipality(@PathVariable Long municipalityId, Model model) {
        Municipality municipality = municipalityService.findById(municipalityId);
        System.out.println("Controller. MunCode: " + municipality.getCode());
        // List<Population> populations = populationService.findByMunicipality(municipality);
        List<Population> populations = municipality.getInhabitants();
        populations = populations.subList(populations.size()-10, populations.size());

        model.addAttribute("municipality", municipality);
        model.addAttribute("populations", populations);
        return "municipality/details";
    }
}
