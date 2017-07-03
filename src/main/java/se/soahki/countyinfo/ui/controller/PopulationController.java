package se.soahki.countyinfo.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import se.soahki.countyinfo.service.PopulationService;

@Controller
public class PopulationController {
    @Autowired
    private PopulationService populationService;

    @RequestMapping("population")
    public String latestPopulation() {
        return "/";
    }
}
