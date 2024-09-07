package com.mycompany.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class DineInReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date reservationDate;
    private Date startTime;
    private Date endTime;
    private int numberOfCustomers;
    private String tableNumber;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false) // Ensure this matches your database foreign key column name
    private Locations locations;  // Reference to Locations entity

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Locations getLocations() {  // Getter for the locations
        return locations;
    }

    public void setLocations(Locations locations) {  // Setter for the locations
        this.locations = locations;
    }
}
