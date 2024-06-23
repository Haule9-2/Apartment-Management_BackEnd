package com.dmp.repositories;

import com.dmp.pojo.User;

public interface UserRepository {
    User getUserByUsername(String username);
    User getUserById(int id);
    User addUser(User user);
    User authUser(String username, String password);
    User authenticateUser(String username, String password);
    boolean isUsernameExists(String username);
    boolean isEmailExists(String email);
    boolean isPhonenumberExists(String phonenumber);
    int changePassword(User user);
    User getUserByUsername_new(String username);
}