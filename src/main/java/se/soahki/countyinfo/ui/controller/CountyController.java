package se.soahki.countyinfo.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.soahki.countyinfo.model.County;
import se.soahki.countyinfo.model.Municipality;
import se.soahki.countyinfo.service.CountyService;
import se.soahki.countyinfo.service.MunicipalityService;
import se.soahki.countyinfo.ui.FlashMessage;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CountyController {

    @Autowired
    private CountyService countyService;

    @Autowired
    private MunicipalityService municipalityService;

    @RequestMapping("/")
    public String start(Model model) {
        String imgUrl = "./map/Sweden_exempel.png";
        model.addAttribute("image", imgUrl);
        return "index";
    }

    @RequestMapping("/counties")
    public String listCounties(Model model) {
        List<County> counties = countyService.findAll();
        model.addAttribute("counties", counties);
        return "county/index";
    }

    @RequestMapping("/counties/{countyId}")
    public String county(@PathVariable Long countyId, Model model) {
        County county = countyService.findById(countyId);
        List<Municipality> municipalities = county.getMunicipalities();

        model.addAttribute("county", county);
        model.addAttribute("municipalities", municipalities);
        return "county/details";
    }


}
