package com.dmp.services.impl;

import com.dmp.pojo.User;
import com.dmp.repositories.UserRepository;
import com.dmp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repo;

//    @Autowired
//    private BCryptPasswordEncoder  passwordEncoder;

    @Override
    public User getUserByUsername(String username) {
        return this.repo.getUserByUsername(username);
    }

    @Override
    public User getUserById(int id) {
        return this.repo.getUserById(id);
    }

    @Override
    public User addUser(User user) {
        return this.repo.addUser(user);
    }

//    @Override
//    public User addAdmin(User user) {
//        user.setRole("Admin");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.getIsActive();
//        return repo.addUser(user);
//    }

    @Override
    public User authUser(String username, String password) {
        return this.repo.authUser(username, password);
    }

    @Override
    public User authenticateUser(String username, String password) {
        return this.repo.authenticateUser(username, password);
    }

    @Override
    public boolean isUsernameExists(String username) {
        return this.repo.isUsernameExists(username);
    }

    @Override
    public boolean isEmailExists(String email) {
        return this.repo.isEmailExists(email);
    }

    @Override
    public boolean isPhonenumberExists(String phonenumber) {
        return this.repo.isPhonenumberExists(phonenumber);
    }

//    @Override
//    public int changePassword(Map<String, String> params) {
//        String username = params.get("username");
//        boolean isUsernameExists = this.repo.isUsernameExists(username);
//
//        if (isUsernameExists != true) {
//            return 2; // Không tìm thấy username để đổi mật khẩu
//        } else {
//            User user = this.getUserByUsername_new(username);
//
//            String oldPassword = params.get("password");
//            String newPassword = params.get("newPassword");
//
//            if (this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
//                user.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
//            } else {
//                return 3; // Sai mật khẩu cũ
//            }
//
//            return this.repo.updateUser(user);
//        }
//    }

    @Override
    public User getUserByUsername_new(String username) {
        return this.repo.getUserByUsername_new(username);
    }
}
