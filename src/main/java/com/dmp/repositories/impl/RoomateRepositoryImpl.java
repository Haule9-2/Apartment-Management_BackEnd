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
import javax.persistence.EntityManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class RoomateRepositoryImpl implements RoomateRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;

    @PersistenceContext
    private EntityManager entityManager;

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
    public List<Roommate> getRoommatesByRoomId(int roomId) {
        String jpql = "SELECT rm FROM Roommate rm " +
                "JOIN rm.contractId c " +
                "JOIN c.roomId r " +
                "WHERE r.id = :roomId";
        return entityManager.createQuery(jpql, Roommate.class)
                .setParameter("roomId", roomId)
                .getResultList();
    }

    @Override
    public List<Roommate> getRoommateByContract(RentalContract contract) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query query = s.createQuery(
                "SELECT r FROM Roommate r WHERE r.contractId.id = :id"
        );
        query.setParameter("id", contract.getId());
        return query.getResultList();
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
        Session session = this.factoryBean.getObject().getCurrentSession();
        Query query = session.createQuery(
                "SELECT m FROM Roommate m WHERE m.contractId.id = :id1 AND m.residentId.id = :id2"
        );
        query.setParameter("id1", contract.getId());
        query.setParameter("id2", resident.getId());
        return query.getResultList().isEmpty();
    }
}
