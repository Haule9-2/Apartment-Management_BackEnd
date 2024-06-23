package com.dmp.repositories;

import com.dmp.pojo.RentalContract;
import com.dmp.pojo.Resident;
import com.dmp.pojo.Roommate;

import java.util.List;

public interface RoomateRepository {
    void addRoommate(Roommate roommate);
    List<Roommate> getRoommateByContract(RentalContract contract);
    List<Roommate> getRoommatesByRoomId(int roomId);
    Roommate getRoommateById(int id);
    void deleteRoommate(int id);
    boolean checkExistence(RentalContract contract, Resident resident);

}