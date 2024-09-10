package com.mycompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationsController {

    @GetMapping("/reservationPage")
    public String reservationPage() {
        return "Reservations";  // This should match the name of your HTML file located in `/src/main/resources/templates/`
    }

     @GetMapping("/reservationsHistory")
    public String reservationsHistoryPage() {
        return "reservationsHistory";
    }
}


