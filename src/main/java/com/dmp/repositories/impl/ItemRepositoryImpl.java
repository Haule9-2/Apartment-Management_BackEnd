package com.dmp.repositories.impl;

import com.dmp.pojo.Item;
import com.dmp.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ItemRepositoryImpl implements ItemRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Item> getItem(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();

        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
            Root<Item> root = criteriaQuery.from(Item.class);


            if (params.containsKey("name")) {
                String name = params.get("name");
                criteriaQuery.where(criteriaBuilder.equal(root.get("name"), name));
            }
            if (params.containsKey("description")) {
                String description = params.get("description");
                criteriaQuery.where(criteriaBuilder.equal(root.get("description"), description));
            }
            Query query = session.createQuery(criteriaQuery);
            List<Item> items = query.getResultList();

            return items;
        } catch (Exception e) {
            System.err.println("Error fetching items: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void addOrUpdateItem(Item item) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        if (item.getId() != null) {
            s.update(item);
        } else {
            s.save(item);
        }
    }

    @Override
    public void setReceivedDate(Item item) {
        Session session = sessionFactory.getObject().getCurrentSession();
        item.setReceivedDate(java.sql.Date.valueOf(LocalDate.now()));
        session.update(item);
    }


}
