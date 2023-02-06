package org.example.resources;

import org.example.models.Customer;
import org.example.models.Room;
import org.example.models.interfaces.IRoom;
import org.example.repository.RoomRepository;
import org.example.services.CustomerService;
import org.example.services.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResorces {

    public static Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(Room room){
        ReservationService.addRoom(room);
    }

    public static void getAllRooms(){
        displayAllRooms(RoomRepository.getInstance().stream().toList());
    }

    public static Collection<Customer> getAllCustomers(){
        return CustomerService.getAllCustomers();
    }

    public static void displayAllReservations(){
        ReservationService.printAllReservations();
    }
    public static void displayAllRooms(List<Room> rooms){
        for (Room x : rooms)
            System.out.println(x);
    }

}
