CREATE TABLE email_template
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    created_at          datetime NULL,
    updated_at          datetime NULL,
    deleted             BIT(1) NOT NULL,
    email_subject       VARCHAR(255) NULL,
    email_template_name VARCHAR(255) NULL,
    event_name          VARCHAR(255) NULL,
    CONSTRAINT pk_emailtemplate PRIMARY KEY (id)
);

ALTER TABLE email_notification
DROP
COLUMN id;

ALTER TABLE email_notification
    ADD id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY;

ALTER TABLE email_notification
    MODIFY id BIGINT AUTO_INCREMENT;

ALTER TABLE email_verification_token
DROP
COLUMN id;

ALTER TABLE email_verification_token
    ADD id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY;

ALTER TABLE email_verification_token
    MODIFY id BIGINT AUTO_INCREMENT;