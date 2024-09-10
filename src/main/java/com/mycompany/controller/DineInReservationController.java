package com.mycompany.controller;

import com.mycompany.model.DineInReservation;
import com.mycompany.model.Locations;
import com.mycompany.service.DineInReservationService;
import com.mycompany.service.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.IntStream;

@Controller
public class DineInReservationController {

    private final DineInReservationService reservationService;
    private final LocationsService locationService;

    @Autowired
    public DineInReservationController(DineInReservationService reservationService, LocationsService locationService) {
        this.reservationService = reservationService;
        this.locationService = locationService;
    }

    @GetMapping("/dineInReservations")
    public String showReservationForm(Model model) {
        model.addAttribute("dineInReservation", new DineInReservation());
        List<Locations> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("tableNumbers", IntStream.rangeClosed(1, DineInReservation.MAX_TABLES).boxed().toList());
        return "dineInReservations";
    }

    @PostMapping("/dineInReservations/save")
    public String saveReservation(@ModelAttribute DineInReservation dineInReservation, RedirectAttributes redirectAttributes) {
        try {
            boolean isCreated = reservationService.createReservation(dineInReservation);
            if (isCreated) {
                redirectAttributes.addFlashAttribute("successMessage", "Reservation created successfully!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "The selected time slot is not available for this table. Please choose a different time.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while creating the reservation.");
        }
        return "redirect:/dineInReservations";
    }

    @GetMapping("/dineInReservationsHistory")
    public String showReservationHistory(Model model) {
        List<DineInReservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "dineInReservationsHistory";
    }

    @GetMapping("/editDineInReservations/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        DineInReservation dineInReservation = reservationService.getReservationById(id);
        if (dineInReservation == null) {
            return "redirect:/dineInReservationsHistory";
        }
        List<Locations> locations = locationService.getAllLocations();
        model.addAttribute("dineInReservation", dineInReservation);
        model.addAttribute("locations", locations);
        model.addAttribute("tableNumbers", IntStream.rangeClosed(1, DineInReservation.MAX_TABLES).boxed().toList());
        return "dineInReservationsEdit";
    }

    @PostMapping("/dineInReservations/update")
    public String updateReservation(@ModelAttribute DineInReservation dineInReservation, RedirectAttributes redirectAttributes) {
        try {
            boolean isUpdated = reservationService.updateReservation(dineInReservation);
            if (isUpdated) {
                redirectAttributes.addFlashAttribute("successMessage", "Reservation updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "The selected time slot is not available for this table. Please choose a different time.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while updating the reservation.");
        }
        return "redirect:/dineInReservationsHistory";
    }

    @GetMapping("/deleteDineInReservations/{id}")
    public String deleteReservation(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            reservationService.deleteReservation(id);
            redirectAttributes.addFlashAttribute("successMessage", "Reservation deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while deleting the reservation.");
        }
        return "redirect:/dineInReservationsHistory";
    }
}