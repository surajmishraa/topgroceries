package com.user.register.controller;

import com.user.register.config.JwtTokenUtil;
import com.user.register.model.JwtRequest;
import com.user.register.model.JwtResponseWithUsername;
import com.user.register.model.OtpRequest;
import com.user.register.model.User;
import com.user.register.services.UserService;
import com.user.register.services.impl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userdetails")
@CrossOrigin(origins = "https://topgrocerys.web.app/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/signUp")
    public User userSignUp(@RequestBody User user){
        userService.signUp(user);
        return user;
    }
    @GetMapping("/hello")
    public String hello(@RequestBody OtpRequest otp){
       return "hi";
    }

    @PostMapping("/validateOtp")
    public Boolean validateOtp(@RequestBody OtpRequest otp){
        if(userService.ValidateOtp(otp.getEmail(),otp.getOtp())==true) {
            return true;
        }
        else {
            return false;
        }
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {


        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if(userService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword())==null){
            throw new UsernameNotFoundException(authenticationRequest.getUsername());
        }

        final String token = jwtTokenUtil.generateToken(userDetails);


        return ResponseEntity.ok(new JwtResponseWithUsername(token,userService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword()).getName()));
    }
    @DeleteMapping("/DeleteAll")
    public void deleteUsers(){
        userService.deleteAllUsers();
    }



}
