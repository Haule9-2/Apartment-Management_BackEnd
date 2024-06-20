package com.dmp.services;

import com.dmp.pojo.Cabinet;
import com.dmp.pojo.Item;

import java.util.List;
import java.util.Map;

public interface CabinetService {
    void createCabinet(Cabinet cabinet);
    List<Object[]> getAllCabinet(Map<String, String> params);
    List<Item> getItemsByCabinetId(int cabinetId, Map<String, String> params);
    Boolean checkStatusCabinet(int id);
    void closeCabinets();
    Boolean ischeckCabinetbyContract(int contractId);

}
