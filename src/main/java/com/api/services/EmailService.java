package com.api.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    Logger logger = Logger.getLogger(EmailService.class.getName());
    public void connectToEmailServer() {
        logger.info("Started execution from connectToEmailServer : Service");
        System.out.println("Connected to email server!!!!");
    }

    public void login() {
        logger.info("Started execution from login : Service");
        System.out.println("User logged in!!!!");
    }

    public void sendMail() {
        logger.info("Started execution from sendMail : Service");
        System.out.println("New mail sent!!!!");
    }
}
