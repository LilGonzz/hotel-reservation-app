package org.example.models.interfaces;

import org.example.models.enums.RoomType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IRoom {

    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public boolean isFree(LocalDate checkin, LocalDate checkout);
}
