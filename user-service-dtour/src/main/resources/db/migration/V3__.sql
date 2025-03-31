
ALTER TABLE user
DROP
COLUMN mobile_number;

ALTER TABLE user
DROP
COLUMN telephone_number;

ALTER TABLE user
    ADD mobile_number VARCHAR(255) NULL;

ALTER TABLE user
    MODIFY mobile_number VARCHAR (255) NULL;


ALTER TABLE user
    ADD telephone_number VARCHAR(255) NULL;

ALTER TABLE user
    MODIFY telephone_number VARCHAR (255) NULL;
