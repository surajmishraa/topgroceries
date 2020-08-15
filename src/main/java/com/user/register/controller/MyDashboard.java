package com.user.register.controller;

import com.user.register.exception.UserNotFoundException;
import com.user.register.model.Address;
import com.user.register.model.User;
import com.user.register.services.AddressService;
import com.user.register.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://topgrocerys.web.app")
@RestController
@RequestMapping("/mydashboard")

public class MyDashboard {
    @Autowired
    private UserService userService;

    @Autowired
    AddressService addressService;

    @GetMapping("/userinfo")
    private User getUserInfo(@RequestHeader("Authorization")String token){
        if(token.length()==0){
            throw new UserNotFoundException("Please login to continue");
        }
        User user=userService.getUserInfo(token.substring(7));
        return user;
    }

    @PostMapping("/updateUserDetails")
    private User updateUserInfo(@RequestBody User user,@RequestHeader("Authorization")String token){
        if(token.length()==0){
            throw new UserNotFoundException("Please login to continue");
        }
        User userinfo=userService.updateUserDetails(token.substring(7),user);
        return userinfo;
    }

    @PostMapping("/addaddress")
    private void addAddress(@RequestHeader("Authorization") String token,@RequestBody Address address){
        if(token.length()==0){
            throw new UserNotFoundException("Please login to continue");
        }
        addressService.addAddress(address,token.substring(7));
    }
    @PutMapping("/updateaddress")
    private void updateaddress(@RequestHeader("Authorization") String token,@RequestBody Address address){
        if(token.length()==0){
            throw new UserNotFoundException("Please login to continue");
        }
        addressService.updateAddress(token.substring(7),address);
    }

    @GetMapping("/useraddresses")
    public List<Address> getUserAddresses(@RequestHeader("Authorization") String token){
        return addressService.findAddresses(token.substring(7));
    }
    @GetMapping("/useraddress/{id}")
    public Address getUserAddress(@RequestHeader("Authorization") String token,@PathVariable Integer id){
        return addressService.findAddress(token.substring(7),id);
    }

    @DeleteMapping("/useraddress/delete/{id}")
    public Address deleteUserAddress(@RequestHeader("Authorization") String token,@PathVariable Integer id){
        System.out.println("fwefwfwefwe");
        return addressService.deleteAddress(token.substring(7),id);
    }
}
