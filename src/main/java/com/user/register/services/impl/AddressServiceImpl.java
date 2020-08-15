package com.user.register.services.impl;

import com.user.register.config.JwtTokenUtil;
import com.user.register.dao.AddressDao;
import com.user.register.dao.UserDao;
import com.user.register.exception.ResourceNotFoundException;
import com.user.register.exception.UserNotFoundException;
import com.user.register.model.Address;
import com.user.register.model.User;
import com.user.register.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public static boolean isValidPinCode(String pinCode)
    {
        String regex= "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$";
        Pattern p = Pattern.compile(regex);
        if (pinCode == null) {
            return false;
        }
        Matcher m = p.matcher(pinCode);
        return m.matches();
    }

    @Override
    public void addAddress(Address address, String token) {
        User user =null;
        String userEmail=jwtTokenUtil.getUsernameFromToken(token);
        if(userDao.findByEmail(userEmail)==null) {
            throw new UserNotFoundException("Please login again");
        }
        else {
            user = (userDao.findByEmail(userEmail));
        }
        address.setUser(user);
        if(this.isValidPinCode(address.getPincode())) {
            addressDao.save(address);
        }
        else{
            throw new UserNotFoundException("Please enter valid pincode");
        }
    }

    @Override
    public List<Address> findAddresses(String token) {
        User user;
        List<Address> address;
        String userEmail=jwtTokenUtil.getUsernameFromToken(token);
        if(userDao.findByEmail(userEmail)==null) {
            throw new UserNotFoundException("Please login again");
        }
        else {
            user = (userDao.findByEmail(userEmail));
        }
        if(addressDao.findByUser(user)==null){
            throw new ResourceNotFoundException("No Address Found");
        }
        address=addressDao.findByUser(user);
        return address;
    }

    @Override
    public Address findAddress(String token, Integer id) {
        User user;
        Address address;
        String userEmail=jwtTokenUtil.getUsernameFromToken(token);
        System.out.println(userDao.findByEmail(userEmail));
        if(userDao.findByEmail(userEmail)==null) {
            throw new UserNotFoundException("Please login again");
        }
        else {
            user = (userDao.findByEmail(userEmail));
        }
        System.out.println(user);
        if(addressDao.findByUser(user)==null){
            throw new ResourceNotFoundException("No Address Found");
        }
        System.out.println(addressDao.findByid(id));
        address=addressDao.findByid(id);
        return address;
    }

    @Override
    public Address updateAddress(String token,  Address address) {
        User user;
        Address address1;
        String userEmail=jwtTokenUtil.getUsernameFromToken(token);
        if(userDao.findByEmail(userEmail)==null) {
            throw new UserNotFoundException("Please login again");
        }
        else {
            user = (userDao.findByEmail(userEmail));
        }
        if(addressDao.findByUser(user)==null){
            throw new ResourceNotFoundException("No Address Found");
        }
        address1=addressDao.save(address);

        return address1;
    }

    @Override
    public Address deleteAddress(String token, Integer id) {
        System.out.println("fygeuihfuowehfoweofeihfwouhoi");
        User user;
        Address address;
        String userEmail=jwtTokenUtil.getUsernameFromToken(token);
        if(userDao.findByEmail(userEmail)==null) {
            throw new UserNotFoundException("Please login again");
        }
        else {
            user = (userDao.findByEmail(userEmail));
        }
        if(addressDao.findByUser(user)==null){
            throw new ResourceNotFoundException("No Address Found");
        }

        address=this.findAddress(token,id);
        System.out.println(address);
        addressDao.delete(address);
        return address;
    }

}
