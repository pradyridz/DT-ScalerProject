package com.dtour.emailservice.service;

import com.dtour.emailservice.exception.InvalidVerificationTokenException;
import com.dtour.emailservice.model.EmailVerificationToken;
import com.dtour.emailservice.repository.EmailVerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailVerificationService {

    private final EmailVerificationTokenRepository tokenRepository;

    @Autowired
    public EmailVerificationService(EmailVerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void saveVerificationToken(UUID userId, String token) {
        // Save the token in the database along with the user's information
        EmailVerificationToken verificationToken = new EmailVerificationToken();
        verificationToken.setCreatedAt(new Date());
        verificationToken.setUpdatedAt(new Date());
        verificationToken.setToken(token);
        verificationToken.setReceiverUserId(userId);
        tokenRepository.save(verificationToken);
    }

    public UUID getUserIdFromVerificationToken(String token) throws InvalidVerificationTokenException {
        Optional<EmailVerificationToken> emailToken = tokenRepository.findEmailVerificationTokenByToken(token);
        if(emailToken.isEmpty()) {
            throw new InvalidVerificationTokenException("This token does not exist");
        }
        UUID userId = emailToken.get().getReceiverUserId();
        if(userId==null){
            throw new UsernameNotFoundException("User Id doesnot exist with this token");
        }
        return userId;
    }
}