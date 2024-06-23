package com.dmp.repositories.impl;

import com.dmp.pojo.Resident;
import com.dmp.repositories.ResidentRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;


import javax.transaction.Transactional;

import java.util.List;


@Repository
@Transactional
public class ResidentRepositoryImpl implements ResidentRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<Resident> getResident() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query query = s.createQuery(
                "SELECT r FROM Resident r JOIN FETCH r.user WHERE r.user.role = :role AND r.user.isActive = true",
                Resident.class
        );
        query.setParameter("role", "Resident");
        return query.getResultList();
    }

    @Override
    public void addOrUpdate(Resident resident) {
        try {
            Session session = this.factoryBean.getObject().getCurrentSession();
            if (checkResident(resident)) {
                session.merge(resident);
            } else {
                session.persist(resident);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Resident getResidentById(int id) {
        try {
            Session s = this.factoryBean.getObject().getCurrentSession();
            Query query = s.createQuery("from Resident r where r.id = :id");
            query.setParameter("id", id);
            return (Resident) query.getSingleResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public List<Resident> findByRoomId(int roomId) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        Query query = session.createQuery(
                "SELECT r FROM Resident r JOIN r.roommates rm WHERE rm.room.id = :roomId",
                Resident.class
        );
        query.setParameter("roomId", roomId);
        return query.getResultList();
    }

    @Override
    public void deleteResident(int id) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        Resident resident = session.get(Resident.class, id);
        if (resident != null) {
            session.delete(resident);
        }
    }
    //hàm này là lấy resident login á m bên m t k dám link bậy
//    @Override
//    public Resident getCurrentResident() {
//        Session s = this.factoryBean.getObject().getCurrentSession();
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        User u = this.userRepository.getUserByUsername(username);
//
//        Resident r = this.getResidentById(u.getId());
//        if(r == null) {
//            throw new AppException(ErrorCode.RESIDENT_NOT_FOUND);
//        }
//
//        return r;
//    }

    @Override
    public Boolean checkResident(Resident resident) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query query = s.createQuery(
                "SELECT (COUNT(r) > 0) FROM Resident r WHERE r.user.id = :id",
                Boolean.class
        );
        query.setParameter("id", resident.getUser().getId());
        return (Boolean) query.getSingleResult();
    }

}