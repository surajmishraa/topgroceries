package com.user.register.services.impl;

import com.user.register.config.Constants;
import com.user.register.dao.UserDao;
import com.user.register.exception.ExistingUserException;
import com.user.register.model.User;
import com.user.register.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public User findByEmail(String email) {
        User user=userDao.findByEmail(email);
        return user;
    }

    @Override
    public User signUp(User user) {
        User userCheck=this.findByEmail(user.getEmail());
        if(this.findByEmail(user.getEmail())!=null&&userCheck.getValidated()==false){
            SendEmail sendEmail = new SendEmail();
            sendEmail.userRegistrationEmail(user);
            return user;
        }
        if(this.findByEmail(user.getEmail())!=null&&user.getValidated()==false){
            throw new ExistingUserException(Constants.EMAIL_EXISTS);
        }
        if(user.getPassword().length()<8){
            throw new ExistingUserException(Constants.PASSWORD_CRITERIA_NOTMATCHED);
        }
        if(this.findByEmail(user.getEmail())==null&&user.getValidated()==false) {
            SendEmail sendEmail = new SendEmail();
            sendEmail.userRegistrationEmail(user);
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
            userDao.save(user);
        }

        return user;
    }

    @Override
    public Boolean ValidateOtp(String email,String otp) {
        if(this.findByEmail(email)==null){
            throw new ExistingUserException("User Doesn't Exist");
        }
        User user=this.findByEmail(email);
        if(user.getOtp().equalsIgnoreCase(otp)){
            System.out.println(user.getOtp()+"    "+otp);
            user.setValidated(true);
        }

        return true;
    }
    @Override
    public User login(String email,String password){
        User userDocument= userDao.findByEmail(email);
        if( userDocument!=null) {
            if (bcryptEncoder.matches(password,userDocument.getPassword()))
            {
                return userDocument;
            }
        }
        return null;
    }

    @Override
    public void deleteAllUsers() {
        userDao.deleteAll();
    }
}
