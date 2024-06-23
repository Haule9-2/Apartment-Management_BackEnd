package com.dmp.services.impl;

import com.dmp.pojo.Questions;
import com.dmp.repositories.QuestionRepository;
import com.dmp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository repo;
    @Override
    public void createQuestion(Questions question) {
        this.repo.createQuestion(question);

    }

    @Override
    public List<Questions> getList(Map<String, String> params) {
        return this.repo.getList(params);
    }

    @Override
    public void addQuestion(Questions question) {
        this.repo.addQuestion(question);

    }

    @Override
    public void updateQuestion(Questions question) {
        this.repo.updateQuestion(question);
    }
}
