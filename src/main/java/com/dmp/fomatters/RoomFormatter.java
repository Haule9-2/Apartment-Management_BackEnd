package com.dmp.fomatters;

import com.dmp.pojo.Room;

import com.dmp.pojo.Services;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoomFormatter implements Formatter<Room> {
    @Override
    public Room parse(String id, Locale locale) throws ParseException {
        Room room = new Room();
        room.setId(Integer.parseInt(id));
        return room;
    }


    @Override
    public String print(Room room, Locale locale) {
        return String.valueOf(room.getId());
    }
}   