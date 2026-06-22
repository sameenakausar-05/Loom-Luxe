package com.loomandluxe.dao;

import java.util.List;

import com.loomandluxe.model.User;

public interface UserDAO {

    boolean registerUser(User user);

    User loginUser(String email, String password);

    User getUserById(int userId);

    User getUserByEmail(String email);

    User getUserByPhone(String phone);


    boolean updateUser(User user);

    boolean updatePassword(int userId, String newPassword);

    boolean isEmailExists(String email);

    boolean isPhoneExists(String phone);
    
    List<User> getAllUsers();

	boolean addUser(User user);
}