package com.dtour.emailservice.controller;

import com.dtour.emailservice.dto.EmailRequestDto;
import com.dtour.emailservice.exception.InvalidVerificationTokenException;
import com.dtour.emailservice.modelbuilder.EmailNotificationBuilder;
import com.dtour.emailservice.service.EmailService;
import com.nimbusds.oauth2.sdk.ParseException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/emails")
public class EmailNotificationController {
    EmailService emailService;

    @Autowired
    public EmailNotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<Void> sendEmailNotification(@ModelAttribute EmailRequestDto emailRequest) throws IOException, MessagingException {
        EmailNotificationBuilder emailNotification = EmailNotificationBuilder.builder()
                .withMailTo(emailRequest.getMailTo())
                .withReceiverUserId(emailRequest.getReceiverUserId())
                .withMailFrom(emailRequest.getMailFrom())
                .withMailBody(emailRequest.getMailBody())
                .withMailSubject(emailRequest.getMailSubject())
                .withReceiverName(emailRequest.getReceiverName())
                .withAttachments(emailRequest.getAttachments())
                .withSenderUserId(emailRequest.getSenderUserId())
                .withService(emailRequest.getServiceName())
                .withEventName(emailRequest.getEventName())
                .build();
        emailService.sendSimpleMessage(emailNotification);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user-registration/verify/email/{emailToken}")
    public ResponseEntity<Void> verifyUserEmailId(@PathVariable String emailToken) throws InvalidVerificationTokenException, IOException, ParseException, URISyntaxException {
        boolean verified= emailService.verifyEmailOfUser(emailToken);
        return verified ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
