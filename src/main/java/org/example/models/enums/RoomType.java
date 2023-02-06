package org.example.models.enums;

import java.util.NoSuchElementException;

public enum RoomType {
    SINGLE(0, "Single"),
    DOUBLE(1, "Double");

    private Integer roomId;
    private String desc;
    private RoomType(Integer roomId, String desc){
        this.roomId = roomId;
        this.desc = desc;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public String getDesc() {
        return desc;
    }

    public static RoomType valueOf(int number){
        for (RoomType x : RoomType.values())
            if(x.roomId == number)
                return x;
        throw new NoSuchElementException("invalid room");
    }
}
