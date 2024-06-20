package com.dmp.services.impl;

import com.dmp.pojo.Surveys;
import com.dmp.repositories.RoomRepository;
import com.dmp.repositories.SurveyRepository;
import com.dmp.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository repo;
    @Override
    public void createSurvey(Surveys survey) {
        this.repo.createSurvey(survey);
    }

    @Override
    public List<Surveys> getAllSurveys(Map<String, String> params) {
        return this.repo.getAllSurveys(params);
    }

    @Override
    public Surveys getSurveyById(int id) {
        return this.repo.getSurveyById(id);
    }

    @Override
    public void updateSurvey(Surveys survey) {
        this.repo.updateSurvey(survey);
    }

    @Override
    public void deleteSurvey(Surveys survey) {
        this.repo.deleteSurvey(survey);

    }

    @Override
    public Long countSurvey() {
        return this.repo.countSurvey();
    }
}
