package com.user.register.services;


import com.user.register.model.User;

public interface UserService {

    User findByEmail(String email);
    User signUp(User user);
    Boolean ValidateOtp(String email,String otp);
    User login(String email,String password);
    void deleteAllUsers();

}
