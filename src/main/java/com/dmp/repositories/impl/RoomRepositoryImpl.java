package com.dmp.repositories.impl;

import com.dmp.pojo.Room;
import com.dmp.pojo.Services;
import com.dmp.repositories.RoomRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
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
    private LocalSessionFactoryBean factoryBean;
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

    @Override
    public void addOrUpdate(Room room) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        try {
            if (room.getId() != null) {
                session.merge(room);
            } else {
                session.persist(room);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Room getRoomById(int id) {
        try {
            Session s = this.factoryBean.getObject().getCurrentSession();
            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);
            Root<Room> root = criteriaQuery.from(Room.class);

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
    public void deleteRoom(int id) {
        try {
            Session s = this.factoryBean.getObject().getCurrentSession();
            Room room = this.getRoomById(id);
            if (room != null) {
                s.delete(room);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace(); // In ra thông tin lỗi
        }

    }

    @Override
    public void updateStatusConTrong_DaThue(Room room) {
        Session s = this.factory.getObject().getCurrentSession();
        Room tmp = this.getRoomById(room.getId());
        tmp.setStatus("đã thuê");
        s.update(tmp);
    }
    public void updateStatus(Room room, String newStatus) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            Room roomToUpdate = s.get(Room.class, room.getId());

            if ("còn trống".equals(newStatus) || "đã thuê".equals(newStatus) || "bảo trì".equals(newStatus)) {
                roomToUpdate.setStatus(newStatus);
                s.update(roomToUpdate);
            } else {
                throw new IllegalArgumentException("Trạng thái mới không hợp lệ");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật trạng thái: " + e.getMessage());
        }
    }
}