package com.dtour.emailservice.service;

import com.dtour.emailservice.exception.InvalidVerificationTokenException;
import com.dtour.emailservice.model.EmailNotification;
import com.dtour.emailservice.model.EmailTemplate;
import com.dtour.emailservice.modelbuilder.EmailNotificationBuilder;
import com.dtour.emailservice.repository.EmailNotificationRepository;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
public class EmailService {
    private final JavaMailSender emailSender;
    private final EmailTemplateService emailTemplateService;
    private final ResourceLoader resourceLoader;
    private final EmailVerificationService verificationService;
    private final EmailNotificationRepository emailNotificationRepository;
    private final ITokenService tokenService;
    private final RestTemplate restTemplate;
    @Value("${user-service.verify.user.email.url}")
    private String verifyEmailBaseUrl;

    @Autowired
    public EmailService(JavaMailSender emailSender,
                        EmailTemplateService emailTemplateService,
                        EmailVerificationService verificationService,
                        ResourceLoader resourceLoader,
                        EmailNotificationRepository emailNotificationRepository,
                        RestTemplate restTemplate,
                        ITokenService tokenService) {
        this.emailSender = emailSender;
        this.emailTemplateService = emailTemplateService;
        this.verificationService = verificationService;
        this.resourceLoader = resourceLoader;
        this.emailNotificationRepository = emailNotificationRepository;
        this.restTemplate = restTemplate;
        this.tokenService = tokenService;
    }

    public void sendSimpleMessage(EmailNotificationBuilder email) throws IOException, MessagingException {
        String templateName = null;
        EmailTemplate emailTemplate = null;
        if (email.getEventName() != null && !email.getEventName().isEmpty()) {
            emailTemplate = emailTemplateService.loadEmailTemplate(email.getEventName());
            templateName = emailTemplate.getEmailTemplateName();
        }
        String token = UUID.randomUUID() + "." + email.getReceiverUserId() + "." + new Date();
        MimeMessage mimeMessage = buildMimeMessage(email, emailTemplate, token);
        emailSender.send(mimeMessage);
        verificationService.saveVerificationToken(email.getReceiverUserId(), token);
        emailNotificationRepository.save(saveEmailMetadata(email, templateName));
    }

    public boolean verifyEmailOfUser(String token) throws InvalidVerificationTokenException, IOException, ParseException, URISyntaxException {
        String decodedToken = new String(Base64.getDecoder().decode(token));
        UUID userId = verificationService.getUserIdFromVerificationToken(decodedToken);
        String verifyEmailUrl = verifyEmailBaseUrl+"?uid="+userId;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenService.getAccessToken().toString());
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Void> response = restTemplate.exchange(verifyEmailUrl, HttpMethod.POST, requestEntity, Void.class);
        return response.getStatusCode().is2xxSuccessful();
    }

    public String createEmailContent(String templateName, String recipientName, String emailToken) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:templates/" + templateName);
        String htmlTemplate = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return htmlTemplate.replace("{name}", recipientName).replace("{emailToken}", emailToken);
    }

    private MimeMessage buildMimeMessage(EmailNotificationBuilder email, EmailTemplate template, String token) throws MessagingException, IOException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
        message.setTo(email.getMailTo());
        message.setFrom(email.getMailFrom());
        String text = "";
        String verificationToken = Base64.getEncoder().encodeToString(token.getBytes());
        if (template != null) {
            text = createEmailContent(template.getEmailTemplateName(), email.getReceiverName(), verificationToken);
            message.setSubject(template.getEmailSubject());
        } else {
            text = email.getMailBody();
            message.setSubject(email.getMailSubject());
        }

        message.setText(text, true);
        return mimeMessage;
    }

    private EmailNotification saveEmailMetadata(EmailNotificationBuilder emailBuilder, String templateName) {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.setMailTo(emailBuilder.getMailTo());
        emailNotification.setMailFrom(emailBuilder.getMailFrom());
        emailNotification.setService(emailBuilder.getService());
        emailNotification.setReceiverUserId(emailBuilder.getReceiverUserId());
        emailNotification.setSenderUserId(emailBuilder.getSenderUserId());
        emailNotification.setCreatedAt(new Date());
        emailNotification.setUpdatedAt(new Date());
        if (templateName != null && !templateName.isBlank()) {
            emailNotification.setTemplateName(templateName);
        } else {
            emailNotification.setMailBody(emailBuilder.getMailBody());
        }
        return emailNotification;
    }
}