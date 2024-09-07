package com.mycompany.controller;

import com.mycompany.model.DeliveryReservation;
import com.mycompany.service.DeliveryReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;


@Controller
public class DeliveryReservationController {

    @Autowired
    private DeliveryReservationService service;

    @GetMapping("/deliveryReservations")
    public ModelAndView getAllDeliveryReservations() {
        List<DeliveryReservation> reservations = service.getAllDeliveryReservations();
        return new ModelAndView("deliveryReservations", "reservations", reservations);
    }

    @GetMapping("/deliveryReservations/{id}")
    public ModelAndView getDeliveryReservation(@PathVariable int id) {
        DeliveryReservation reservation = service.getReservationById(id);
        return new ModelAndView("editDeliveryReservation", "reservation", reservation);
    }

    @PostMapping("/deliveryReservations/update")
    public String updateDeliveryReservation(@ModelAttribute DeliveryReservation reservation) {
        service.saveDeliveryReservation(reservation);
        return "redirect:/deliveryReservations";
    }

}
