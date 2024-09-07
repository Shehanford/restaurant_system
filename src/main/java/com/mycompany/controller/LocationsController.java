package com.mycompany.controller;
import com.mycompany.model.Locations;
import com.mycompany.service.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class LocationsController {

    @Autowired
    private LocationsService service;

    @GetMapping("/locations")
    public String showLocationsForm(Model model){
        Locations locations = new Locations();
        model.addAttribute("locations", new Locations());
        return "locations";
    }

    @PostMapping("/locations/save")
    public String saveLocations(@ModelAttribute Locations locations){
        service.save(locations);
        return "redirect:/locationsList";
    }

    @GetMapping("/locationsList")
    public ModelAndView getAllLocations(){
        List<Locations> listLocations = service.getAllLocations();
        return new ModelAndView("locationsList","locations", listLocations);
    }

    @RequestMapping("/deleteLocationsList/{id}")
    public String deleteLocationsList(@PathVariable("id") int id){
        service.deleteById(id);
        return "redirect:/locationsList";
    }

    @RequestMapping("/editLocations/{id}")
    public String editLocations(@PathVariable("id") int id, Model model){
        Locations locations = service.getLocationsById(id);
        model.addAttribute("locations", locations);
        return "locationsEdit";
    }

    /*@GetMapping("/api/locations")
    @ResponseBody
    public List<Locations> getAllLocationsJson() {
        return service.getAllLocations();
    }*/

}
