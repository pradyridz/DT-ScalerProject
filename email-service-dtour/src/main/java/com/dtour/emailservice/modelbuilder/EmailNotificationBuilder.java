package com.dtour.emailservice.modelbuilder;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
public class EmailNotificationBuilder {
    private String mailTo;
    private UUID receiverUserId;
    private String receiverName;
    private String mailFrom;
    private final String mailSubject;
    private String mailBody;
    private Date createdAt;
    private Date updatedAt;
    private UUID senderUserId;
    private String service;
    private String eventName;
    private List<MultipartFile> attachments;

    private EmailNotificationBuilder(Builder builder) {
        this.mailTo = builder.mailTo;
        this.receiverName = builder.receiverName;
        this.receiverUserId = builder.receiverUserId;
        this.mailFrom = builder.mailFrom;
        this.mailSubject = builder.mailSubject;
        this.mailBody = builder.mailBody;
        this.senderUserId = builder.senderUserId;
        this.service = builder.service;
        this.eventName = builder.eventName;
        this.attachments = builder.attachments;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String mailTo;
        private String receiverName;
        private UUID receiverUserId;
        private String mailFrom;
        private String mailSubject;
        private String mailBody;
        private UUID senderUserId;
        private String service;
        private String eventName;
        private List<MultipartFile> attachments;

        private Builder() {
        }

        public Builder withMailTo(String to) {
            this.mailTo = to;
            return this;
        }

        public Builder withReceiverName(String receiverName) {
            this.receiverName = receiverName;
            return this;
        }

        public Builder withReceiverUserId(UUID receiverUserId) {
            this.receiverUserId = receiverUserId;
            return this;
        }

        public Builder withMailFrom(String from) {
            this.mailFrom = from;
            return this;
        }

        public Builder withMailSubject(String subject) {
            this.mailSubject = subject;
            return this;
        }

        public Builder withMailBody(String body) {
            this.mailBody = body;
            return this;
        }

        public Builder withSenderUserId(UUID senderUserId) {
            this.senderUserId = senderUserId;
            return this;
        }

        public Builder withService(String service) {
            this.service = service;
            return this;
        }

        public Builder withAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
            return this;
        }

        public Builder withEventName(String eventName) {
            this.eventName = eventName;
            return this;
        }

        public EmailNotificationBuilder build() {
            return new EmailNotificationBuilder(this);
        }
    }
}
