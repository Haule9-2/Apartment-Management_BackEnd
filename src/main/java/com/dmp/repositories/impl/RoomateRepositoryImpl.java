package com.dmp.repositories.impl;

import com.dmp.pojo.RentalContract;
import com.dmp.pojo.Resident;
import com.dmp.pojo.Roommate;
import com.dmp.repositories.RoomateRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class RoomateRepositoryImpl implements RoomateRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;
    @Override
    public void addRoommate(Roommate roommate) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        try {
            if (roommate.getId() != null) {
                session.merge(roommate);
            } else {
                session.persist(roommate);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Roommate> getRoommate(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Roommate> cq = cb.createQuery(Roommate.class);
        Root<Roommate> r = cq.from(Roommate.class);
        cq.select(r);
        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(cb.like(r.get("someAttribute"), String.format("%%%s%%", kw)));
            }
            String contractId = params.get("contractId");
            if (contractId != null && !contractId.isEmpty()) {
                predicates.add(cb.equal(r.get("contractId").get("id"), Integer.parseInt(contractId)));
            }
            String residentId = params.get("residentId");
            if (residentId != null && !residentId.isEmpty()) {
                predicates.add(cb.equal(r.get("residentId").get("id"), Integer.parseInt(residentId)));
            }
        }

        cq.where(predicates.toArray(Predicate[]::new));
        cq.orderBy(cb.desc(r.get("id")));
        Query q = s.createQuery(cq);

        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("Roommate.PAGE_SIZE"));

                q.setMaxResults(pageSize);
                q.setFirstResult((p - 1) * pageSize);
            }
        }

        return q.getResultList();
    }

    @Override
    public Roommate getRoommateById(int id) {
        try {
            Session s = this.factoryBean.getObject().getCurrentSession();
            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<Roommate> criteriaQuery = builder.createQuery(Roommate.class);
            Root<Roommate> root = criteriaQuery.from(Roommate.class);

            Predicate idPredicate = builder.equal(root.get("id"), id);
            criteriaQuery.where(idPredicate);

            return s.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteRoommate(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Roommate roommate = s.get(Roommate.class, id);
        if (roommate != null) {
            s.delete(roommate);
        }
    }

    @Override
    public boolean checkExistence(RentalContract contract, Resident resident) {
        try {
            Session s = this.factoryBean.getObject().getCurrentSession();
            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<Roommate> root = criteriaQuery.from(Roommate.class);

            Predicate contractPredicate = builder.equal(root.get("contractId"), contract);
            Predicate residentPredicate = builder.equal(root.get("residentId"), resident);

            criteriaQuery.select(builder.count(root)).where(builder.and(contractPredicate, residentPredicate));

            Long count = s.createQuery(criteriaQuery).getSingleResult();
            return count > 0;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
