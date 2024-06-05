package com.dmp.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dmp.pojo.Services;
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
        // Kết hợp tất cả các điều kiện thành một Predicate
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
        Session s = this.factoryBean.getObject().getCurrentSession();
        if (service.getId() != null) {
            s.merge(service);
        }
        else
            s.persist(service);
    }

    @Override
    public Services getServiceById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Services.class, id);
    }
}