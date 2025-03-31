package com.dtour.emailservice.repository;

import com.dtour.emailservice.model.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, UUID> {

    public Optional<EmailVerificationToken> findEmailVerificationTokenByToken(String token);
}
