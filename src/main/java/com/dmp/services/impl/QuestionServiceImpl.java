package com.dmp.services.impl;

import com.dmp.pojo.Questions;
import com.dmp.repositories.QuestionRepository;
import com.dmp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository repo;
    @Override
    public void createQuestion(Questions question) {
        this.repo.createQuestion(question);

    }
}
