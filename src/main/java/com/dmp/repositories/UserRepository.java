package com.dmp.repositories;

import com.dmp.pojo.User;

public interface UserRepository {
    User getUserByUsername(String username);
    User addUser(User user);
    User authUser(String username, String password);
    User authenticateUser(String username, String password);
//    boolean userExistsByUsername(String username);
}