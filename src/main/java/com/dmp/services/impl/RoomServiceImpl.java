package com.dmp.services.impl;

import com.dmp.pojo.Room;
import com.dmp.repositories.RoomRepository;
import com.dmp.repositories.ServiceRepository;
import com.dmp.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository repo;

    @Override
    public List<Room> getRooms(Map<String, String> params) {
        return this.repo.getRooms(params);
    }

    @Override
    public void addOrUpdate(Room room) {
        this.repo.addOrUpdate(room);
    }

    @Override
    public Room getRoomById(int id) {
        return this.repo.getRoomById(id);
    }

    @Override
    public void deleteService(int id) {
        this.repo.deleteRoom(id);
    }
}
