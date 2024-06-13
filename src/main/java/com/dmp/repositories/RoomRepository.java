package com.dmp.repositories;

import com.dmp.pojo.Room;

import java.util.List;
import java.util.Map;

public interface RoomRepository {
    List<Room> getRooms(Map<String, String> params);
    void addOrUpdate(Room room);
    Room getRoomById(int id);
    void deleteRoom(int id);
    void updateStatusConTrong_DaThue(Room room);
    void updateStatus(Room room, String newStatus);
}
