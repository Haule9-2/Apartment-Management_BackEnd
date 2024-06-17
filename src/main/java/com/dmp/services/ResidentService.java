package com.dmp.services;

import com.dmp.pojo.Resident;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ResidentService {
    List<Resident> getResident();
    void addOrUpdate(Resident resident);
    Resident getResidentById(int id);
    void deleteResident(int id);
    //    Resident getCurrentResident();
    Boolean checkResident(Resident resident);
}
