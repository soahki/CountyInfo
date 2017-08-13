package se.soahki.countyinfo.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.soahki.countyinfo.model.County;
import se.soahki.countyinfo.model.Population;
import se.soahki.countyinfo.service.PopulationService;
import se.soahki.countyinfo.utilities.statistics.Statistics;

import java.util.*;

@Controller
public class PopulationController {
    @Autowired
    private PopulationService populationService;

    @RequestMapping("statistik/befolkning/{year}")
    public String getTotalPopulationOfYear(@PathVariable Integer year, Model model) {
        List<Population> populationsOfYear = populationService.findByYear(year);
        List<Integer> populationValues = new ArrayList<>();
        Map<County, Integer> countyPopulations = new HashMap<>();
        Map<Integer, County> populationsCounty = new HashMap<>();
        for (Population population : populationsOfYear) {
            populationValues.add(population.getInhabitants());
            County county = population.getMunicipality().getCounty();
            if (countyPopulations.get(county) == null) {
                countyPopulations.put(county, population.getInhabitants());
            } else {
                Integer incrementInhabitants = population.getInhabitants() + countyPopulations.get(county);
                countyPopulations.put(county, incrementInhabitants);
            }
        }
        for (County county : countyPopulations.keySet()) {
            populationsCounty.put(countyPopulations.get(county), county);
        }
        List<Integer> sortedPopulationsCounty = new ArrayList<>(populationsCounty.keySet());
        Collections.sort(sortedPopulationsCounty);
        Collections.reverse(sortedPopulationsCounty);

        Statistics stats = new Statistics(populationValues);
        model.addAttribute("stats", stats);
        model.addAttribute("counties", countyPopulations);
        model.addAttribute("populationsMap", populationsCounty);
        model.addAttribute("populations", sortedPopulationsCounty);
        return "statistik";
    }

    private void topCounties(int TopNOfCounties) {
        List<County> topCounties = new ArrayList<>();


    }
}
