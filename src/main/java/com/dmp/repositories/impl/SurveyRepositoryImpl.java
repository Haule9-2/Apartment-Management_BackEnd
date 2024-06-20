package com.dmp.repositories.impl;

import com.dmp.pojo.Surveys;
import com.dmp.repositories.SurveyRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
@Transactional
public class SurveyRepositoryImpl implements SurveyRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Override
    public void createSurvey(Surveys survey) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        s.persist(survey);
    }

    @Override
    public List<Surveys> getAllSurveys(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query query = s.createNamedQuery("Surveys.findAll", Surveys.class);
        return query.getResultList();
    }

    @Override
    public Surveys getSurveyById(int id) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(Surveys.class);
        Root<Surveys> root = criteriaQuery.from(Surveys.class);

        Predicate idPredicate = builder.equal(root.get("SurveyID"), id);
        criteriaQuery.where(idPredicate);

        TypedQuery<Surveys> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public void updateSurvey(Surveys survey) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.update(survey);

    }

    @Override
    public void deleteSurvey(Surveys survey) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.delete(survey);

    }

    @Override
    public Long countSurvey() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rD = q.from(Surveys.class);

        q.multiselect(b.count(rD.get("id")));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(b.function("MONTH", Integer.class, rD.get("SurveyDate")), LocalDate.now().getMonthValue()));

        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);

        return (Long) query.getSingleResult();
    }
}
