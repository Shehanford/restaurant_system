package com.mycompany.repository;

import com.mycompany.model.DeliveryReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryReservationRepository extends JpaRepository<DeliveryReservation, Integer> {
}
