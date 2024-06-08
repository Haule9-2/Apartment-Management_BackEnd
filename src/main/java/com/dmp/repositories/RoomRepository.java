package com.dmp.repositories;

import com.dmp.pojo.Room;

import java.util.List;
import java.util.Map;

public interface RoomRepository {
    List<Room> getRooms(Map<String, String> params);
}
