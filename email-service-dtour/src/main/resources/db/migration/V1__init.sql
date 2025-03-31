CREATE TABLE email_notification
(
    id               BINARY(16) NOT NULL,
    created_at       datetime NULL,
    updated_at       datetime NULL,
    deleted          BIT(1)       NOT NULL,
    mail_to          VARCHAR(255) NOT NULL,
    receiver_user_id BINARY(16) NULL,
    mail_from        VARCHAR(255) NOT NULL,
    mail_subject     VARCHAR(255) NULL,
    mail_body        VARCHAR(255) NULL,
    sender_user_id   BINARY(16) NULL,
    service          VARCHAR(255) NULL,
    template_name    VARCHAR(255) NULL,
    CONSTRAINT pk_emailnotification PRIMARY KEY (id)
);

CREATE TABLE email_verification_token
(
    id               BINARY(16) NOT NULL,
    created_at       datetime NULL,
    updated_at       datetime NULL,
    deleted          BIT(1) NOT NULL,
    receiver_user_id BINARY(16) NULL,
    token            VARCHAR(255) NULL,
    CONSTRAINT pk_emailverificationtoken PRIMARY KEY (id)
);