package com.dmp.services;

import com.dmp.pojo.RentalContract;
import com.dmp.pojo.Resident;
import com.dmp.pojo.Roommate;

import java.util.List;

public interface RoommateService {
    List<Roommate> getRoommateByContract(RentalContract contract);
    Roommate getRoommateById(int id);
    void deleteRoommate(int id);
    boolean checkExistence(RentalContract contract, Resident resident);
}
