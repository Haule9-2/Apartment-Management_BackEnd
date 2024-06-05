package com.dmp.repositories;

import com.dmp.pojo.Services;

import java.util.List;
import java.util.Map;

public interface ServiceRepository {
    List<Services> getService(Map<String, String> params);

    void addOrUpdate(Services service);
    Services getServiceById(int id);
}
