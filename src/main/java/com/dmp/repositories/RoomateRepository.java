package com.dmp.repositories;

import com.dmp.pojo.RentalContract;
import com.dmp.pojo.Resident;
import com.dmp.pojo.Roommate;
import com.dmp.pojo.Services;

import java.util.List;
import java.util.Map;

public interface RoomateRepository {
    void addRoommate(Roommate roommate);
    List<Roommate> getRoommateByContract(RentalContract contract);
    Roommate getRoommateById(int id);
    void deleteRoommate(int id);
    boolean checkExistence(RentalContract contract, Resident resident);

}
