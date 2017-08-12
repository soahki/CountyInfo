package se.soahki.countyinfo.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.soahki.countyinfo.model.Population;
import se.soahki.countyinfo.service.PopulationService;
import se.soahki.countyinfo.utilities.statistics.Statistics;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PopulationController {
    @Autowired
    private PopulationService populationService;

    @RequestMapping("statistik/befolkning/{year}")
    public String getTotalPopulationOfYear(@PathVariable Integer year, Model model) {
        List<Population> populationsOfYear = populationService.findByYear(year);
        List<Integer> populationValues = new ArrayList<>();
        for (Population population : populationsOfYear) {
            populationValues.add(population.getInhabitants());
        }

        Statistics stats = new Statistics(populationValues);
        model.addAttribute("stats", stats);
        return "statistik";
    }

    private void statistics(List<Population> populations) {
        double[] values = new double[populations.size()];
        for (int i = 0; i < populations.size(); i++) {
            values[i] = populations.get(i).getInhabitants();
        }
    }
}
