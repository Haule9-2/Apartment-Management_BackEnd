package com.dmp.services;

import com.dmp.pojo.Room;
import com.dmp.pojo.Services;

import java.util.List;
import java.util.Map;

public interface RoomService {
    List<Room> getRooms(Map<String, String> params);
    void addOrUpdate(Room room);
    Room getRoomById(int id);
    void deleteRoom(int id);
    void updateStatusConTrong_DaThue(Room room);
    void updateStatus(Room room, String newStatus);
}
