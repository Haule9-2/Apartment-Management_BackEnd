package com.dmp.repositories.impl;

import com.dmp.pojo.Questions;
import com.dmp.repositories.QuestionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void createQuestion(Questions question) {
        Session s = sessionFactory.getCurrentSession();
        s.save(question);
    }
}
