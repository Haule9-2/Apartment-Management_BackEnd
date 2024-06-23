package com.dmp.services;

import com.dmp.pojo.Resident;

import java.util.List;

public interface ResidentService {
    List<Resident> getResident();
    void addOrUpdate(Resident resident);
    Resident getResidentById(int id);
    void deleteResident(int id);
    //    Resident getCurrentResident();
    Boolean checkResident(Resident resident);
//    Resident getResidentByRoomId(int roomId);

}
