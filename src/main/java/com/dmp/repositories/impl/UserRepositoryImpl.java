package com.dmp.repositories.impl;

import com.dmp.pojo.User;
import com.dmp.repositories.UserRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import javax.persistence.Query;
import java.util.Objects;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username = :username");
        q.setParameter("username", username);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (User) q.getSingleResult();
    }

    @Override
    public User getUserById(int id) {
        try {
            Session session = this.factory.getObject().getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);

            Predicate idPredicate = builder.equal(root.get("userId"), id);

            Predicate otherCondition = builder.equal(root.get("isActive"), Boolean.TRUE);

            Predicate finalPredicate = builder.and(idPredicate, otherCondition);

            criteriaQuery.where(finalPredicate);
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User addUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        Serializable id = s.save(user);
        return s.get(User.class, id);
    }


    @Override
    public User authUser(String username, String password) {
        User u = this.getUserByUsername(username);
        return (u != null && Objects.equals(password, u.getPassword())) ? u : null;
    }

    @Override
    public User authenticateUser(String username, String password) {
        User u = this.getUserByUsername(username);
        return (u != null && Objects.equals(password, u.getPassword())) ? u : null;
    }

    @Override
    public boolean isUsernameExists(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("SELECT COUNT(*) FROM User WHERE username = :username");
        query.setParameter("username", username);
        long count = (long) query.getSingleResult();
        return count > 0;
    }

    @Override
    public boolean isEmailExists(String email) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("SELECT COUNT(*) FROM User WHERE email = :email");
        query.setParameter("email", email);
        long count = (long) query.getSingleResult();
        return count > 0;
    }

    @Override
    public boolean isPhonenumberExists(String phonenumber) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("SELECT COUNT(*) FROM User WHERE phone = :phone");
        query.setParameter("phone", phonenumber);
        long count = (long) query.getSingleResult();
        return count > 0;
    }


    @Override
    public int changePassword(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.update(user);
            return 1;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public User getUserByUsername_new(String username) {
        try {
            Session session = this.factory.getObject().getCurrentSession();
            Query query = session.createQuery("FROM User WHERE username=:username AND isActive = TRUE");
            query.setParameter("username", username);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}
