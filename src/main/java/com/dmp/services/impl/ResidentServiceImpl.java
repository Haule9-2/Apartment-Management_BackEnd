package com.dmp.services.impl;

import com.dmp.pojo.Resident;
import com.dmp.repositories.ResidentRepository;
import com.dmp.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResidentServiceImpl implements ResidentService {
    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public List<Resident> getResident(Map<String, String> params) {
        return residentRepository.getResident(params);
    }

    @Override
    public void addOrUpdate(Resident resident) {
        residentRepository.addOrUpdate(resident);
    }

    @Override
    public Resident getResidentById(int id) {
        return residentRepository.getResidentById(id);
    }

    @Override
    public void deleteResident(int id) {
        residentRepository.deleteResident(id);
    }
}
