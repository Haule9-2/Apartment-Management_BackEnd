package com.dmp.repositories;

import com.dmp.pojo.Responses;

import java.util.List;

public interface ResponsesRepository {
    List<Responses> getResponsesBySurveyAndResident(int surveyId, int residentId);
    void createResponse(Responses response);
}
