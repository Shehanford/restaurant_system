package com.mycompany.repository;

import com.mycompany.model.DineInReservation;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DineInReservationRepository extends JpaRepository<DineInReservation, Integer> {
   /* @Query("SELECT r FROM DineInReservation r WHERE r.tableNumber = :tableNumber AND r.reservationDate = :date AND NOT (r.endTime <= :startTime OR r.startTime >= :endTime)")
    List<DineInReservation> findByTableAndDate(@Param("tableNumber") String tableNumber, @Param("date") Date date, @Param("startTime") Date startTime, @Param("endTime") Date endTime);*/
}
