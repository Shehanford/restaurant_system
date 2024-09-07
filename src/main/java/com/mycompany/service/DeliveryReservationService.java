package com.mycompany.service;

import com.mycompany.model.DeliveryReservation;
import com.mycompany.repository.DeliveryReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryReservationService {

    @Autowired
    private DeliveryReservationRepository deliveryRepository;

    public List<DeliveryReservation> getAllDeliveryReservations() {
        return deliveryRepository.findAll();
    }


    public DeliveryReservation saveDeliveryReservation(DeliveryReservation reservation) {
        return deliveryRepository.save(reservation);
    }

    public DeliveryReservation getReservationById(int id) {
        Optional<DeliveryReservation> reservation = deliveryRepository.findById(id);
        return reservation.orElse(null);
    }

}