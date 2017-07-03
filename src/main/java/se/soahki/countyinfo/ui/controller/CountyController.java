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
    public String start() {
        return "index";
    }

    @RequestMapping("/counties")
    public String listCounties(Model model) {
        List<County> counties = countyService.findAll();
        model.addAttribute("counties", counties);
        return "county/index";
    }

    @RequestMapping("/counties/{countyId}")
    public String category(@PathVariable Long countyId, Model model) {
        County county = countyService.findById(countyId);
        List<Municipality> municipalities = county.getMunicipalities();

        model.addAttribute("county", county);
        model.addAttribute("municipalities", municipalities);
        return "county/details";
    }


    // Form for adding a new county
    @RequestMapping("counties/add")
    public String formNewCounty(Model model) {
        // TODO: Add model attributes needed for new form
        if(!model.containsAttribute("county")) {
            model.addAttribute("county",new County());
        }
        model.addAttribute("action","/categories");
        model.addAttribute("heading","New Category");
        model.addAttribute("submit","Add");

        return "county/form";
    }

    // Add a county
    @RequestMapping(value = "/counties", method = RequestMethod.POST)
    public String addCounty(@Valid County county, BindingResult result, RedirectAttributes redirectAttributes) {
        // TODO: Add category if valid data was received
        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);

            // Add  category if invalid was received
            redirectAttributes.addFlashAttribute("county",county);

            // Redirect back to the form
            return "redirect:/counties/add";
        }

        countyService.save(county);

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Category successfully added!", FlashMessage.Status.SUCCESS));

        // TODO: Redirect browser to /categories
        return "redirect:/counties";
    }

}
