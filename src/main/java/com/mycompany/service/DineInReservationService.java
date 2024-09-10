package com.mycompany.service;

import com.mycompany.model.DineInReservation;
import com.mycompany.repository.DineInReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DineInReservationService {

    private final DineInReservationRepository reservationRepository;

    @Autowired
    public DineInReservationService(DineInReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public boolean createReservation(DineInReservation reservation) {
        List<DineInReservation> overlappingReservations = reservationRepository.findOverlappingReservations(
                reservation.getLocation().getId(),
                reservation.getReservationDate(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getTableNumber()
        );

        if (overlappingReservations.isEmpty()) {
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateReservation(DineInReservation reservation) {
        List<DineInReservation> overlappingReservations = reservationRepository.findOverlappingReservations(
                reservation.getLocation().getId(),
                reservation.getReservationDate(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getTableNumber()
        );

        // Remove the current reservation from the list of overlapping reservations
        overlappingReservations.removeIf(r -> r.getId().equals(reservation.getId()));

        if (overlappingReservations.isEmpty()) {
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    public List<DineInReservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public DineInReservation getReservationById(int id) {
        Optional<DineInReservation> reservation = reservationRepository.findById(id);
        return reservation.orElse(null);
    }

    @Transactional
    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);
    }
}