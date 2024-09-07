package com.mycompany.service;

import com.mycompany.model.DineInReservation;
import com.mycompany.repository.DineInReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycompany.ExceptionHandler.TableNotAvailableException;

import java.util.Date;
import java.util.List;

@Service
public class DineInReservationService {

    @Autowired
    private DineInReservationRepository reservationrepo;

  /*  public boolean isTableAvailable(String tableNumber, Date date, Date startTime, Date endTime) {
        List<DineInReservation> reservations = dineInReservationRepository.findByTableAndDate(tableNumber, date, startTime, endTime);
        return reservations.isEmpty();
    }

    public DineInReservation saveReservation(DineInReservation reservation) {
        if (!isTableAvailable(reservation.getTableNumber(), reservation.getReservationDate(), reservation.getStartTime(), reservation.getEndTime())) {
            throw new TableNotAvailableException("Table not available for the specified time.");
        }
        return dineInReservationRepository.save(reservation);
    }

    public List<DineInReservation> getAllReservations() {
        return dineInReservationRepository.findAll();
    }*/

    public void save(DineInReservation dineInReservation){
        reservationrepo.save(dineInReservation);
    }

    public List<DineInReservation> getAllDineInReservation(){
        return reservationrepo.findAll();
    }

    public void deleteById(int id){
        reservationrepo.deleteById(id);
    }

    public DineInReservation getDineInReservationById(int id){
        return reservationrepo.findById(id).get();
    }
}