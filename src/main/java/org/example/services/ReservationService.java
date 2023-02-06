package org.example.services;

import org.example.models.Customer;

import org.example.models.Reservation;
import org.example.models.Room;
import org.example.models.interfaces.IRoom;
import org.example.repository.ReservationRepository;
import org.example.repository.RoomRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ReservationService {

    public static void addRoom(Room room){
        RoomRepository.getInstance().add(room);
    }

    public static Map<String, Room> findRooms(LocalDate checkin, LocalDate checkout){
        var rooms = RoomRepository.getInstance();
        if(rooms.isEmpty()){
            throw new NoSuchElementException("there is no rooms in the hotel!");
        }
        Map<String, Room> mapRooms = new HashMap<>();
        for(Room x : rooms){
            if (x.isFree(checkin, checkout)){
                mapRooms.put(x.getRoomNumber(), x);
            }
        }
        return mapRooms;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer){
        List<Reservation> r = new ArrayList<>();
        for(Reservation x : ReservationRepository.getInstance()){
            if(x.getCustomer().equals(customer)){
                r.add(x);
            }
        }
        return r;
    }

    public static void printAllReservations(){
        ReservationRepository.getInstance().forEach(x -> System.out.println(x));
    }

    public static Set<Reservation> getReservations() {
        return ReservationRepository.getInstance();
    }

    public static void addReservation(Reservation r){
        ReservationRepository.getInstance().add(r);
    }
    public static IRoom findRoom(String roomNumber){
        Optional<Room> room = RoomRepository.getInstance().stream().filter(x -> x.getRoomNumber().equals(roomNumber)).findFirst();
        return room.isEmpty() ? null : room.get();
    }

}
