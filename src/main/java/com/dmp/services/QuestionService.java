package com.dmp.services;

import com.dmp.pojo.Questions;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    void createQuestion(Questions question);
    List<Questions> getList(Map<String, String> params);

    void addQuestion(Questions question);

    void updateQuestion(Questions question);
}
