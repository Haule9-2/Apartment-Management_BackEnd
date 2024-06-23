
package com.dmp.repositories;

import com.dmp.pojo.Resident;

import java.util.List;

public interface ResidentRepository {
    List<Resident> getResident();
    void addOrUpdate(Resident resident);
    Resident getResidentById(int id);
    void deleteResident(int id);
    //    Resident getCurrentResident();
    Boolean checkResident(Resident resident);
    List<Resident> findByRoomId(int roomId);


}
