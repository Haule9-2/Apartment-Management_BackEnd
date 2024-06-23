package com.dmp.services.impl;

import com.dmp.pojo.Complaint;
import com.dmp.repositories.ComplaintRepository;
import com.dmp.services.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ComplainServiceImpl implements ComplainService {
    @Autowired
    private ComplaintRepository repo;
    @Override
    public List<Complaint> getAllComplaint(Map<String, String> params) {
        return this.repo.getAllComplaint(params);
    }

    @Override
    public List<Complaint> getAllComplaintByApartmentId(int id) {
        return this.repo.getAllComplaintByApartmentId(id);
    }

    @Override
    public void deleteComplainttById(int id) {
        this.repo.deleteComplainttById(id);

    }

    @Override
    public void addComplaint(Complaint c) {
        this.repo.addComplaint(c);

    }

    @Override
    public void updateComplaint(Complaint c) {
        this.repo.updateComplaint(c);

    }

    @Override
    public Complaint getComplaintById(int id) {
        return this.repo.getComplaintById(id);
    }

    @Override
    public Long countComplaints() {
        return this.repo.countComplaints();
    }
}
