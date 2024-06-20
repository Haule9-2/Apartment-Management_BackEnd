package com.dmp.services.impl;

import com.dmp.pojo.Responses;
import com.dmp.repositories.ResponsesRepository;
import com.dmp.services.ResponsesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsesServiceImpl implements ResponsesService {
    @Autowired
    private ResponsesRepository repo;

    @Override
    public List<Responses> getResponsesBySurveyAndResident(int surveyId, int residentId) {
        return this.repo.getResponsesBySurveyAndResident(surveyId, residentId);
    }

    @Override
    public void createResponse(Responses response) {
        this.repo.createResponse(response);

    }
}
