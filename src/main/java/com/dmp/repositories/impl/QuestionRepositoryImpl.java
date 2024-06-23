package com.dmp.repositories.impl;

import com.dmp.pojo.Questions;
import com.dmp.repositories.QuestionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Questions> getList(Map<String, String> params) {
        Session s = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Questions> query = builder.createQuery(Questions.class);
        Root<Questions> root = query.from(Questions.class);
        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            if (params.containsKey("title")) {
                predicates.add(builder.like(root.get("title"), "%" + params.get("title") + "%"));
            }
        }

        query.select(root).where(predicates.toArray(new Predicate[0]));

        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public void addQuestion(Questions question) {
        Session s = sessionFactory.getCurrentSession();
        s.save(question);

    }

    @Override
    public void updateQuestion(Questions question) {
        Session s = sessionFactory.getCurrentSession();
        s.update(question);
    }
}
