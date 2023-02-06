package org.example.models;

import org.example.models.enums.RoomType;
import org.example.models.interfaces.IRoom;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType typeRoom;
    private boolean isFree;
    private List<Check> dates;

    public Room(){}
    public Room(String roomNumber, Double price, RoomType typeRoom) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.typeRoom = typeRoom;
        this.isFree = true;
        dates = new ArrayList<>();
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.price;
    }

    @Override
    public RoomType getRoomType() {
        return this.typeRoom;
    }
    @Override
    public boolean isFree(LocalDate checkIn, LocalDate checkOut) {
        if(checkAvaible(checkIn, checkOut))
            return true;
        return false;
    }

    public boolean isFree(){
        return this.isFree;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTypeRoom(RoomType typeRoom) {
        this.typeRoom = typeRoom;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public List<Check> getDates() {
        return dates;
    }

    public void setDates(List<Check> dates) {
        this.dates = dates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return roomNumber.equals(room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", typeRoom=" + typeRoom +
                ", avaible: " + isFree +
                '}';
    }

    private boolean checkAvaible(LocalDate checkIn, LocalDate checkOut){

        for(Check x : dates){
            if ((checkIn.isAfter(x.getCheckIn()) && checkIn.isBefore(x.getCheckOut()))
                    || (checkOut.isAfter(x.getCheckIn()) && checkOut.isBefore(x.getCheckOut()))
                    || (checkIn.equals(x.getCheckIn()) || checkIn.equals(x.getCheckOut()))
                    || ((checkOut.equals(x.getCheckIn()) || checkOut.equals(x.getCheckOut())))){
                return false;
            }

            else if(checkIn.isBefore(x.getCheckIn()) && checkOut.isAfter(x.getCheckOut())){
                return false;
            }
        }
        return true;
    }


}
