package com.user.register.dao;


import com.user.register.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {


    User findByEmail(String email);

    User findById(long userId);

    



}
