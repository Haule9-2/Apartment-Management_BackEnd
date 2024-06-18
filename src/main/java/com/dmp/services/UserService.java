package com.dmp.services;

import com.dmp.pojo.User;

public interface UserService {
    User getUserByUsername(String username);
//    User addUser(User user);
    User authenticateUser(String username, String password);
}
