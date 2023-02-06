package org.example.repository;

import org.example.models.Room;
import org.example.models.interfaces.IRoom;

import java.util.HashSet;
import java.util.Set;

public class RoomRepository {

    private static final Set<Room> rooms = new HashSet<>();

    private RoomRepository(){}

    public static Set<Room> getInstance(){
        if(rooms == null){
            return new HashSet<>();
        }
        return rooms;
    }
}
