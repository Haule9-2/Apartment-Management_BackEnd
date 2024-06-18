package com.dmp.repositories.impl;

import com.dmp.pojo.User;
import com.dmp.repositories.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
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

//    @Override
//    public boolean userExistsByUsername(String username) {
//        return this.getUserByUsername(username) != null;
//    }
    @Override
    public User authenticateUser(String username, String password) {
        User u = this.getUserByUsername(username);
        return (u != null && Objects.equals(password, u.getPassword())) ? u : null;
    }
}
