package com.user.register.services.impl;

import com.user.register.model.User;
import org.springframework.mail.SimpleMailMessage;

import java.util.function.Supplier;

public class SendEmail {
    public void userRegistrationEmail(User userData) {
        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(userData.getEmail());
        registrationEmail.setSubject("Confirm your email for TopGroceries ");
        String otp=otps.get();
        userData.setOtp(otp);
        registrationEmail.setText("Please enter this otp to confirm email "+ otp);
        registrationEmail.setFrom("noreply@smc.com");
        EmailService.sendEmail(registrationEmail);
    }
    Supplier<String> otps=()->{
        String otp="";
        for(int i=1;i<=6;i++){
            otp=otp+(int)(Math.random()*10);
        }
        return otp;
    };

}
