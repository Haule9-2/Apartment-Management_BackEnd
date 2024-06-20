package com.dmp.repositories;

import com.dmp.pojo.Surveys;

import java.util.List;
import java.util.Map;

public interface SurveyRepository {
    void createSurvey(Surveys survey);
    List<Surveys> getAllSurveys(Map<String, String> params);
    Surveys getSurveyById(int id);
    void updateSurvey(Surveys survey);
    void deleteSurvey(Surveys survey);
    Long countSurvey();

}
