package com.dmp.services;

import com.dmp.pojo.Responses;

import java.util.List;

public interface ResponsesService {
    List<Responses> getResponsesBySurveyAndResident(int surveyId, int residentId);
    void createResponse(Responses response);
}
