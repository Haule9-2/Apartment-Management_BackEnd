package com.dmp.repositories;

import com.dmp.pojo.Cabinet;
import com.dmp.pojo.Item;

import java.util.List;
import java.util.Map;

public interface CabinetRepository {
    void createCabinet(Cabinet cabinet);
    List<Object[]> getAllCabinet(Map<String, String> params);
    List<Item> getItemsByCabinetId(int cabinetId, Map<String, String> params);
    Boolean checkStatusCabinet(int cabinetId);
    Boolean checkActiveCabinet(int cabinetId);
    void closeCabinets();
    Boolean ischeckCabinetbyContract(int contractId);
}
