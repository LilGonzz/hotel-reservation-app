package org.example.resources;

import org.example.models.*;
import org.example.models.interfaces.IRoom;
import org.example.repository.ReservationRepository;
import org.example.repository.RoomRepository;
import org.example.services.CustomerService;
import org.example.services.ReservationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class HotelResources {
    public static Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }

    public static void createCustomer(String email, String firstName, String lastName){
        CustomerService.addCustomer(email, firstName, lastName);
    }

    public static Reservation bookRoom(Customer customer, Room room, LocalDate checkin, LocalDate checkout){
        Reservation r = new Reservation(room, customer,checkin, checkout);
        Check dates = new Check(checkin, checkout);
        room.getDates().add(dates);
        ReservationService.addReservation(r);
        return r;
    }

    public static Collection<Reservation> getCustomerReservation(Customer c){
        return ReservationService.getCustomersReservation(c);
    }

    public static Map<String, Room> findRoom(LocalDate checkin, LocalDate checkout){
        return ReservationService.findRooms(checkin, checkout);
    }

    public static IRoom findRoom(String roomNumber){
        return ReservationService.findRoom(roomNumber);
    }

    public static boolean validEmailFormat(String email){
        return checkEmailFormat(email);
    }

    private static boolean checkEmailFormat(String email){
        Pattern pattern = Pattern.compile("^(.+)@(.+).([a-zA-Z])$");
        return pattern.matcher(email).matches();
    }


}
