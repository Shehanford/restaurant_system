package com.mycompany.controller;

import com.mycompany.model.DineInReservation;
import com.mycompany.reporting.DineInFilesExporter;
import com.mycompany.service.DineInReservationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class DineInReservationRestController {

    @Autowired
    DineInReservationService dineInReservationService;

    @Autowired
    DineInFilesExporter dineInFilesExporter;

    @GetMapping("/dineInOrder/export/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        List<DineInReservation> dineInReservationList = dineInReservationService.getAllReservations();
        dineInFilesExporter.exportToCSV(dineInReservationList, response);
    }

    @GetMapping("/dineInOrder/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        List<DineInReservation> dineInReservationList = dineInReservationService.getAllReservations();
        dineInFilesExporter.exportToPDF(dineInReservationList, response);
    }

}
