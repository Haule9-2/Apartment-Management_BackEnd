package com.dmp.services.impl;

import com.dmp.pojo.Cabinet;
import com.dmp.pojo.Item;
import com.dmp.repositories.CabinetRepository;
import com.dmp.services.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CabinetServiceImpl implements CabinetService {
    @Autowired
    private CabinetRepository repo;
    @Override
    public void createCabinet(Cabinet cabinet) {
        this.repo.createCabinet(cabinet);

    }

    @Override
    public List<Object[]> getAllCabinet(Map<String, String> params) {
        return this.repo.getAllCabinet(params);
    }

    @Override
    public List<Item> getItemsByCabinetId(int cabinetId, Map<String, String> params) {
        return this.repo.getItemsByCabinetId(cabinetId, params);
    }

    @Override
    public Boolean checkStatusCabinet(int id) {
        return this.repo.checkStatusCabinet(id);
    }

    @Override
    public void closeCabinets() {
        this.repo.closeCabinets();

    }

    @Override
    public Boolean ischeckCabinetbyContract(int contractId) {
        return this.repo.ischeckCabinetbyContract(contractId);
    }
}
