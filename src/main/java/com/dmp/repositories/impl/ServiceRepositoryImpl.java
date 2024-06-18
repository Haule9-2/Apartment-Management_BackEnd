package com.dmp.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dmp.pojo.Services;
import org.hibernate.HibernateException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.dmp.repositories.ServiceRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class ServiceRepositoryImpl implements ServiceRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;

    @Override
    public List<Services> getService(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Services> cq = cb.createQuery(Services.class);
        Root<Services> r = cq.from(Services.class);
        cq.select(r);
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(cb.like(r.get("name"), String.format("%%%s%%", kw)));
            }
            String fromPrice = params.get("fromPrice");
            if (fromPrice != null && !fromPrice.isEmpty()) {
                predicates.add(cb.greaterThanOrEqualTo(r.get("price"), Double.parseDouble(fromPrice)));
            }
            String toPrice = params.get("toPrice");
            if (toPrice != null && !toPrice.isEmpty()) {
                predicates.add(cb.lessThanOrEqualTo(r.get("price"), Double.parseDouble(toPrice)));
            }
        }
        predicates.add(cb.equal(r.get("isActive"), (short) 1));
        cq.where(predicates.toArray(Predicate[]::new));
        cq.orderBy(cb.desc(r.get("id")));
        Query q = s.createQuery(cq);
        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("Service.PAGE_SIZE"));

                q.setMaxResults(pageSize);
                q.setFirstResult((p - 1) * pageSize);
            }
        }
            List<Services> services = q.getResultList();
        return services;
    }

    @Override
    public void addOrUpdate(Services service) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        try {
            // Đặt is_active thành 1 trước khi merge hoặc persist
            service.setIsActive((byte) 1);
            if (service.getId() != null) {
                session.merge(service);
            } else {
                session.persist(service);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Services getServiceById(int id) {
        try {
            Session s = this.factoryBean.getObject().getCurrentSession();
            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<Services> criteriaQuery = builder.createQuery(Services.class);
            Root<Services> root = criteriaQuery.from(Services.class);

            Predicate isActivePredicate = builder.equal(root.get("isActive"), (short) 1);
            Predicate idPredicate = builder.equal(root.get("id"), id);
            Predicate finalPredicate = builder.and(isActivePredicate, idPredicate);

            criteriaQuery.where(finalPredicate);

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
    public void deleteService(int id) {
        try {
            Session s = this.factoryBean.getObject().getCurrentSession();
            Services service = this.getServiceById(id);
            if (service != null) {
                s.delete(service);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace(); // In ra thông tin lỗi
        }
    }


}