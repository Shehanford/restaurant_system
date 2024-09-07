package com.mycompany.controller;

import com.mycompany.model.Locations;
import com.mycompany.model.DineInReservation;
import com.mycompany.service.LocationsService;
import com.mycompany.service.DineInReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;

@Controller
public class DineInReservationController {

    @Autowired
    private DineInReservationService dineInReservationService;

    @Autowired
    private LocationsService locationService;

   /* // Shows the form to add a new reservation
    @GetMapping("/dineInReservations")
    public String showReservationForm(Model model){
        List<Locations>listLocations= locationService.getAllLocations();
        model.addAttribute("listLocations",listLocations);
        DineInReservation dineInReservation = new DineInReservation();
        model.addAttribute("dineInReservation", new DineInReservation());
        return "dineInReservations";
    }

    @PostMapping("/dineInReservations/save")
    public String saveReservation(@ModelAttribute DineInReservation dineInReservation, Model model) {
        DineInReservation savedReservation = dineInReservationService.saveReservation(dineInReservation);
        if (savedReservation != null) {
            return "redirect:/listDineInReservations";  // Redirect to the history page after successful save

        } else {
            model.addAttribute("dineInReservation", dineInReservation);
            model.addAttribute("locations", locationService.getAllLocations());
            return "dineInReservations";
        }
    }

    // Lists all reservations (history)
    @GetMapping("/listDineInReservations")
    public String listReservations(Model model) {
        model.addAttribute("reservations", dineInReservationService.findAllReservations());
        return "listDineInReservations";  // Ensure this template lists the reservations
    }*/

    @GetMapping("/dineInReservations")
    public String showReservationsPage(Model model){
        List<Locations>listLocations= locationService.getAllLocations();
        model.addAttribute("listLocations",listLocations);
        DineInReservation dineInReservation = new DineInReservation();
        model.addAttribute("dineInReservation", new DineInReservation());
        return "dineInReservations";
    }

    @PostMapping("/dineInReservations/save")
    public String saveAReservation(@ModelAttribute  DineInReservation dineInReservation){
        dineInReservationService.save(dineInReservation);
        return "redirect:/listDineInReservations";
    }

    @GetMapping("/listDineInReservations")
    public ModelAndView getAllDineInReservation(){
        List<DineInReservation> listDineInReservations = dineInReservationService.getAllDineInReservation();
        return new ModelAndView("listDineInReservations","listDineInReservations",listDineInReservations);
    }

    @RequestMapping("/deleteDineInReservations/{id}")
    public String deleteDineInReservations(@PathVariable("id") int id){
        dineInReservationService.deleteById(id);
        return "redirect:/listDineInReservations";
    }

    /*@RequestMapping("/editDineInReservations/{id}")
    public String editLocations(@PathVariable("id") int id, Model model){
        Booking booking = appService.getBookingById(id);
        List<Country>listCountries= countryService.getAllCountries();
        model.addAttribute("listCountries",listCountries);
        List<JobTypes>listJobs = jobService.getAllJobs();
        model.addAttribute("listJobs", listJobs);
        model.addAttribute("booking", booking);
        return "appointmentEdit";
    }


    @PostMapping("/appointment/update")
    public String updateAppointment(@ModelAttribute Booking booking){
        appService.save(booking);
        return "redirect:/history";
    }*/

}