package com.dmp.services.impl;

import com.dmp.pojo.RentalContract;
import com.dmp.pojo.Resident;
import com.dmp.pojo.Roommate;
import com.dmp.repositories.RoomateRepository;
import com.dmp.services.RoommateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoommateServiceImpl implements RoommateService {
    @Autowired
    private RoomateRepository repo;

    @Override
    public void addRoommate(Roommate roommate) {
        this.repo.addRoommate(roommate);
    }

    @Override
    public List<Roommate> getRoommate(Map<String, String> params) {
        return this.repo.getRoommate(params);
    }

    @Override
    public Roommate getRoommateById(int id) {
        return this.repo.getRoommateById(id);
    }

    @Override
    public void deleteRoommate(int id) {
        this.repo.deleteRoommate(id);
    }

    @Override
    public boolean checkExistence(RentalContract contract, Resident resident) {
        return repo.checkExistence(contract, resident);
    }
}
