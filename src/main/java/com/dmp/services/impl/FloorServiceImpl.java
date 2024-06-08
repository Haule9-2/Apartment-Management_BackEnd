package com.dmp.services.impl;

import com.dmp.pojo.Floor;
import com.dmp.repositories.FloorRepository;
import com.dmp.services.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FloorServiceImpl implements FloorService {
    @Autowired
    private FloorRepository repo;
    @Override
    public List<Floor> getFloor(Map<String, String> params) {
        return this.repo.getFloor(params);
    }
}
