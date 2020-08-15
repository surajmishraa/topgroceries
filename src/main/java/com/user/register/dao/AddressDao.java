package com.user.register.dao;

import com.user.register.model.Address;
import com.user.register.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao extends JpaRepository<Address,Integer> {

    List<Address> findByUser(User user);
    Address findByid(Integer id);
}
