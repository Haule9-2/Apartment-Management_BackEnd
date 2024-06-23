package com.dmp.services;

import com.dmp.pojo.Floor;

import java.util.List;
import java.util.Map;

public interface FloorService {
    List<Floor> getFloor(Map<String, String> params);
    Floor getFloorById(int id);
}
