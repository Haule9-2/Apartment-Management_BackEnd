package com.dmp.services;

import com.dmp.pojo.Services;

import java.util.List;
import java.util.Map;

public interface ServiceService {
    List<Services> getServices(Map<String, String> params);
    void addOrUpdate(Services service);
    Services getServiceById(int id);
}
