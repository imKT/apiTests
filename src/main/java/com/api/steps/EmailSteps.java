package com.api.steps;

import com.api.services.EmailService;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailSteps {
    //EmailService emailService = new EmailService();
    @Autowired
    EmailService emailService;
    Logger logger = Logger.getLogger(EmailSteps.class.getName());
    @Value("${msg}")
    public String msg;
    @Value("${globalMsg}")
    public String globalMsg;

    @Given("User is connected to email server")
    public void connectToEmailServer(){
        logger.info("Started execution from connectToEmailServer");
        logger.info("Message from env.prop : " + msg);
        logger.info("Global Message from global.prop : " + globalMsg);
        emailService.connectToEmailServer();
    }
    @When("User logs in")
    public void login(){
        logger.info("Started execution from login");
        emailService.login();
    }
    @Then("Mail is sent")
    public void sendMail(){
        logger.info("Started execution from sendMail");
        emailService.sendMail();
    }
}
