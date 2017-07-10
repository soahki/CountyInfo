package se.soahki.countyinfo.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdministrativeController {

    @RequestMapping("/om")
    public String about() {
        return "om";
    }

    @RequestMapping("/statistik")
    public String stats() {
        return "statistik";
    }
}
