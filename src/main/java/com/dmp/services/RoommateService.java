package com.dmp.services;

import com.dmp.pojo.RentalContract;
import com.dmp.pojo.Resident;
import com.dmp.pojo.Roommate;

import java.util.List;
import java.util.Map;

public interface RoommateService {
    void addRoommate(Roommate roommate);
    List<Roommate> getRoommate(Map<String, String> params);
    Roommate getRoommateById(int id);
    void deleteRoommate(int id);
    boolean checkExistence(RentalContract contract, Resident resident);
}
