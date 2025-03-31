package com.dtour.emailservice.service;

import com.dtour.emailservice.model.EmailTemplate;
import com.dtour.emailservice.repository.EmailTemplateReposiroy;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailTemplateService {
    private final EmailTemplateReposiroy templateReposiroy;

    public EmailTemplateService(EmailTemplateReposiroy templateReposiroy) {
        this.templateReposiroy = templateReposiroy;
    }

    public EmailTemplate loadEmailTemplate(String eventName) throws IOException {
        return templateReposiroy.findEmailTemplateByEventName(eventName).get();
        // Load the HTML template file

    }
}
