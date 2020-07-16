package com.user.register.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {

    public static JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        EmailService.mailSender=mailSender;
    }

    @Async
    public static void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }
}
