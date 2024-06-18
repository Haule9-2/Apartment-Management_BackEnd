package com.dmp.services.impl;

import com.dmp.pojo.User;
import com.dmp.repositories.UserRepository;
import com.dmp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User authenticateUser(String username, String password) {
        User user = userRepository.getUserByUsername(username);

        if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}
