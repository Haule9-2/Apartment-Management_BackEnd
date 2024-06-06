package com.dmp.services.impl;

import com.dmp.pojo.Services;
import org.springframework.stereotype.Service;
import com.dmp.repositories.ServiceRepository;
import com.dmp.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class ServiceServiceImpl implements  ServiceService {

    @Autowired
    private ServiceRepository repo;

    @Override
    public List<Services> getServices(Map<String, String> params) {
        return this.repo.getService(params);
    }

    @Override
    public void addOrUpdate(Services service) {
        this.repo.addOrUpdate(service);

    }

    @Override
    public Services getServiceById(int id) {
        return this.repo.getServiceById(id);
    }

    @Override
    public void deleteService(int id) {
        this.repo.deleteService(id);
    }

}

