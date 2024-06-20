package com.dmp.repositories.impl;

import com.dmp.pojo.Responses;
import com.dmp.repositories.ResponsesRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ResponsesRepositoryImpl implements ResponsesRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<Responses> getResponsesBySurveyAndResident(int surveyId, int residentId) {
        Session session = factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Responses.class);
        Root<Responses> root = cq.from(Responses.class);

        cq.where(
                cb.equal(root.get("surveyID").get("surveyID"), surveyId),
                cb.equal(root.get("residentID").get("id"), residentId)
        );

        TypedQuery<Responses> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void createResponse(Responses response) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(response);
    }
}
