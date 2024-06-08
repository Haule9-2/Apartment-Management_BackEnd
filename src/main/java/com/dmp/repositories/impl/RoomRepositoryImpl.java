package com.dmp.repositories.impl;

import com.dmp.pojo.Room;
import com.dmp.repositories.RoomRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class RoomRepositoryImpl implements RoomRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Room> getRooms(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Room> q = b.createQuery(Room.class);
        Root r = q.from(Room.class);
        q.select(r);
        List<Predicate> predicates = new ArrayList<>();
        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("number"), String.format("%%%s%%", kw)));
        }
        String status = params.get("status");
        System.out.println(status);
        if (status != null && !status.equals("all")) {
            predicates.add(b.like(r.get("status"), String.format("%%%s%%", status)));
        }
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        List<Room> rooms = query.getResultList();

        return rooms;
    }
}
