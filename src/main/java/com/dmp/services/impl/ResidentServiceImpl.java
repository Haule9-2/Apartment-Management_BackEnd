package com.dmp.services.impl;

import com.dmp.pojo.Resident;
import com.dmp.repositories.ResidentRepository;
import com.dmp.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ResidentServiceImpl implements ResidentService {
    @Autowired
    private ResidentRepository repo;


    @Override
    public List<Resident> getResident() {
        return this.repo.getResident();
    }

    @Override
    public void addOrUpdate(Resident resident) {
        this.repo.addOrUpdate(resident);

    }

    @Override
    public Resident getResidentById(int id) {
        return this.repo.getResidentById(id);
    }

    @Override
    public void deleteResident(int id) {
        this.repo.deleteResident(id);

    }

    @Override
    public Boolean checkResident(Resident resident) {
        return this.repo.checkResident(resident);
    }

}
