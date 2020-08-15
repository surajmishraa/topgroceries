package com.user.register.services;

import com.user.register.model.Address;

import java.util.List;

public interface AddressService {
    void addAddress(Address address, String token);
    List<Address> findAddresses(String token);
    Address findAddress(String token,Integer id);
    Address updateAddress(String token, Address address);
    Address deleteAddress(String token,Integer id);
}

