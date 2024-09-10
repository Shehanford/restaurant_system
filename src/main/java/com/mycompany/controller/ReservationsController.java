package com.mycompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationsController {

    @GetMapping("/reservationPage")
    public String reservationPage() {
        return "Reservations";
    }

    @GetMapping("/reservationsHistory")
    public String reservationsHistoryPage() {
        return "reservationsHistory";
    }
}