package com.mycompany.repository;

import com.mycompany.model.DineInReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DineInReservationRepository extends JpaRepository<DineInReservation, Integer> {

    @Query("SELECT r FROM DineInReservation r WHERE r.location.id = :locationId AND r.reservationDate = :date " +
            "AND r.tableNumber = :tableNumber AND ((r.startTime <= :startTime AND r.endTime > :startTime) OR (r.startTime < :endTime AND r.endTime >= :endTime))")
    List<DineInReservation> findOverlappingReservations(Integer locationId, LocalDate date, String startTime, String endTime, Integer tableNumber);

}