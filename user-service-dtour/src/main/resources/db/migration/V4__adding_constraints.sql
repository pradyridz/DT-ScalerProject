ALTER TABLE user
    ADD CONSTRAINT uc_user_email_address UNIQUE (email_address);

ALTER TABLE user
    ADD CONSTRAINT uc_user_mobile_number UNIQUE (mobile_number);

ALTER TABLE address
    MODIFY address_line1 VARCHAR (255) NOT NULL;

ALTER TABLE address
    MODIFY country VARCHAR (255) NOT NULL;

ALTER TABLE user
    MODIFY email_address VARCHAR (255) NOT NULL;

ALTER TABLE agent
    MODIFY last_name VARCHAR (255) NOT NULL;

ALTER TABLE user
    MODIFY mobile_number VARCHAR (255) NOT NULL;

ALTER TABLE address
    MODIFY postal_code VARCHAR (255) NOT NULL;

ALTER TABLE user
    MODIFY status_id BINARY(16) NOT NULL;