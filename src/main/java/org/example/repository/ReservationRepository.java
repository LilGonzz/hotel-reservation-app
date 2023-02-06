package org.example.repository;

import org.example.models.Customer;
import org.example.models.Reservation;

import java.util.HashSet;
import java.util.Set;

public class ReservationRepository {

    private static final Set<Reservation> reservations = new HashSet<>();

    private ReservationRepository(){}

    public static Set<Reservation> getInstance(){
        if (reservations == null){
            return new HashSet<Reservation>();
        }
        return reservations;
    }
}
