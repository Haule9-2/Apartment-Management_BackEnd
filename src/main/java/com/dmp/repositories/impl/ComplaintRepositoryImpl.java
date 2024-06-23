package com.dmp.repositories.impl;

import com.dmp.pojo.Complaint;
import com.dmp.repositories.ComplaintRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ComplaintRepositoryImpl implements ComplaintRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;


    @Override
    public List<Complaint> getAllComplaint(Map<String, String> params) {
        Session session = factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Complaint.class);
        Root<Complaint> root = cq.from(Complaint.class);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            if (params.containsKey("title")) {
                String title = params.get("title");
                predicates.add(cb.like(root.get("title"), "%" + title + "%"));
            }
            if (params.containsKey("content")) {
                String content = params.get("content");
                predicates.add(cb.like(root.get("content"), "%" + content + "%"));
            }
            if (params.containsKey("fromDate") && params.containsKey("toDate")) {
                String fromDateStr = params.get("fromDate");
                String toDateStr = params.get("toDate");

                if (!fromDateStr.isEmpty() && !toDateStr.isEmpty()) {
                    try {

                        Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDateStr);
                        Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(toDateStr);

                        predicates.add(cb.between(root.get("createdDate"), fromDate, toDate));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        cq.where(predicates.toArray(new Predicate[0]));

        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    @Override
    public List<Complaint> getAllComplaintByApartmentId(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Complaint> cq = cb.createQuery(Complaint.class);
        Root<Complaint> root = cq.from(Complaint.class);
        cq.select(root);
        cq.where(cb.equal(root.get("residentId").get("apartmentId"), id));

        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void deleteComplainttById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        Complaint complaint = session.get(Complaint.class, id);
        if (complaint != null) {
            session.delete(complaint);
        }

    }

    @Override
    public void addComplaint(Complaint c) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(c);

    }

    @Override
    public void updateComplaint(Complaint c) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.update(c);

    }

    @Override
    public Complaint getComplaintById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Complaint.findById");
        q.setParameter("id", id);
        List<Complaint> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}