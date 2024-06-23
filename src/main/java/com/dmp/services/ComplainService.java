package com.dmp.services;

import com.dmp.pojo.Complaint;

import java.util.List;
import java.util.Map;

public interface ComplainService {
    List<Complaint> getAllComplaint(Map<String, String> params);
    List<Complaint> getAllComplaintByApartmentId(int id);

    void deleteComplainttById(int id);

    void addComplaint(Complaint c);

    void updateComplaint(Complaint c);

    Complaint getComplaintById(int id);
}
