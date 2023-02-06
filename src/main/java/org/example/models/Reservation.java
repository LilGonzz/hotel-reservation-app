package org.example.models;

import org.example.models.interfaces.IRoom;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Reservation {
    private IRoom room;
    private Customer customer;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Double totalCost;

    public Reservation(){}
    public Reservation(IRoom room, Customer customer, LocalDate checkIn, LocalDate checkOut) {
        this.room = room;
        this.customer = customer;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        setTotalCost(checkIn, checkOut);
    }

    private void setTotalCost(LocalDate checkIn, LocalDate checkOut){
        long diff = ChronoUnit.DAYS.between(checkIn,checkOut);
        this.totalCost = room.getRoomPrice() * (diff);
    }

    public Double getTotalCost(){
        return this.totalCost;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return room.equals(that.room) && customer.equals(that.customer) && checkIn.equals(that.checkIn) && checkOut.equals(that.checkOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, customer);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "room=" + room +
                ", customer=" + customer +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", total cost= "+ totalCost +
                '}';
    }
}
