package com.dmp.services;

import com.dmp.pojo.Resident;

import java.util.List;
import java.util.Map;

public interface ResidentService {
    List<Resident> getResident(Map<String, String> params);
    void addOrUpdate(Resident resident);
    Resident getResidentById(int id);
    void deleteResident(int id);
}