package com.example.microservice.notificationservice.service;

import com.example.microservice.notificationservice.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    public void sendEmail(OrderPlacedEvent order){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(order.getEmailAddress());
        mailMessage.setSubject("Order Placed");
        mailMessage.setText("Hi "+order.getName()+",\nYour orderNo is : "+order.getOrderNumber()+"\n Thank You");
        javaMailSender.send(mailMessage);
        log.info("mail notification sent successfully");

    }
}
