package com.dtour.emailservice.repository;

import com.dtour.emailservice.model.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailNotificationRepository extends JpaRepository<EmailNotification,Long> {
}
