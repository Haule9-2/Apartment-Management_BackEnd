package com.dmp.repositories.impl;

import com.dmp.pojo.Resident;
import com.dmp.repositories.ResidentRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ResidentRepositoryImpl implements ResidentRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<Resident> getResident(Map<String, String> params) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Resident> cq = cb.createQuery(Resident.class);
        Root<Resident> root = cq.from(Resident.class);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(cb.like(root.get("avatar"), String.format("%%%s%%", kw)));
            }
        }
        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(root.get("id")));

        Query query = session.createQuery(cq);
        return query.getResultList();
    }


    @Override
    public void addOrUpdate(Resident resident) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        if (resident.getId() != null && session.find(Resident.class, resident.getId()) != null) {
            session.merge(resident);
        } else {
            session.persist(resident);
        }
    }

    @Override
    public Resident getResidentById(int id) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        return session.get(Resident.class, id);
    }

    @Override
    public void deleteResident(int id) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        Resident resident = session.get(Resident.class, id);
        if (resident != null) {
            session.delete(resident);
        }
    }
}