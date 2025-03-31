package com.dtour.emailservice.repository;

import com.dtour.emailservice.model.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailTemplateReposiroy extends JpaRepository<EmailTemplate,Long> {
    public Optional<EmailTemplate> findEmailTemplateByEventName(String eventName);
}
