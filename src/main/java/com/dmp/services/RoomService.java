package com.dmp.services;

import com.dmp.pojo.Room;
import com.dmp.pojo.Services;

import java.util.List;
import java.util.Map;

public interface RoomService {
    List<Room> getRooms(Map<String, String> params);
}
