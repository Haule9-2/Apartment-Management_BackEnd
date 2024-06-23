package com.dmp.services;

import com.dmp.pojo.User;

public interface UserService {
    User getUserByUsername(String username);
    User getUserById(int id);
    User addUser(User user);
//    User addAdmin(User user);
    User authUser(String username, String password);
    User authenticateUser(String username, String password);
    boolean isUsernameExists(String username);
    boolean isEmailExists(String email);
    boolean isPhonenumberExists(String phonenumber);
//    int changePassword(Map<String, String> params);
    User getUserByUsername_new(String username);
}
