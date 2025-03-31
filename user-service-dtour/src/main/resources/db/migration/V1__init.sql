CREATE TABLE address
(
    id            BINARY(16) NOT NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    deleted       BIT(1) NOT NULL,
    address_line1 VARCHAR(255) NULL,
    address_line2 VARCHAR(255) NULL,
    address_line3 VARCHAR(255) NULL,
    city          VARCHAR(255) NULL,
    district      VARCHAR(255) NULL,
    state         VARCHAR(255) NULL,
    country       VARCHAR(255) NULL,
    postal_code   VARCHAR(255) NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE `admin`
(
    user_id              BINARY(16) NOT NULL,
    first_name           VARCHAR(255) NULL,
    last_name            VARCHAR(255) NULL,
    permanent_address_id BINARY(16) NULL,
    current_address_id   BINARY(16) NULL,
    CONSTRAINT pk_admin PRIMARY KEY (user_id)
);

CREATE TABLE admin_roles
(
    admin_user_id BINARY(16) NOT NULL,
    roles_id      BINARY(16) NOT NULL
);

CREATE TABLE agent
(
    user_id                        BINARY(16) NOT NULL,
    first_name                     VARCHAR(255) NULL,
    last_name                      VARCHAR(255) NULL,
    permanent_address_id           BINARY(16) NULL,
    current_address_id             BINARY(16) NULL,
    company_id                     BINARY(16) NULL,
    curr_same_as_permanent_address BIT(1) NOT NULL,
    CONSTRAINT pk_agent PRIMARY KEY (user_id)
);

CREATE TABLE agent_roles
(
    agent_user_id BINARY(16) NOT NULL,
    roles_id      BINARY(16) NOT NULL
);

CREATE TABLE company
(
    id                   BINARY(16) NOT NULL,
    created_at           datetime NULL,
    updated_at           datetime NULL,
    deleted              BIT(1) NOT NULL,
    travel_agency_number VARCHAR(255) NULL,
    address_id           BINARY(16) NULL,
    website_url          VARCHAR(255) NULL,
    pan_number           VARCHAR(255) NULL,
    pan_image            VARCHAR(255) NULL,
    gst_image            VARCHAR(255) NULL,
    CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE user
(
    id                 BINARY(16) NOT NULL,
    created_at         datetime NULL,
    updated_at         datetime NULL,
    deleted            BIT(1) NOT NULL,
    name               VARCHAR(255) NULL,
    mobile_number      INT    NOT NULL,
    telephone_number   INT    NOT NULL,
    email_address      VARCHAR(255) NULL,
    encrypted_password VARCHAR(255) NULL,
    status_id          BINARY(16) NULL,
    email_verified     BIT(1) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    id         BINARY(16) NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    deleted    BIT(1) NOT NULL,
    name       VARCHAR(255) NULL,
    code       TINYINT NULL,
    CONSTRAINT pk_userrole PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id  BINARY(16) NOT NULL,
    roles_id BINARY(16) NOT NULL
);

CREATE TABLE user_status
(
    id         BINARY(16) NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    deleted    BIT(1) NOT NULL,
    name       VARCHAR(255) NULL,
    code       TINYINT NULL,
    CONSTRAINT pk_userstatus PRIMARY KEY (id)
);

ALTER TABLE `admin`
    ADD CONSTRAINT FK_ADMIN_ON_CURRENT_ADDRESS FOREIGN KEY (current_address_id) REFERENCES address (id);

ALTER TABLE `admin`
    ADD CONSTRAINT FK_ADMIN_ON_PERMANENT_ADDRESS FOREIGN KEY (permanent_address_id) REFERENCES address (id);

ALTER TABLE `admin`
    ADD CONSTRAINT FK_ADMIN_ON_USERID FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE agent
    ADD CONSTRAINT FK_AGENT_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE agent
    ADD CONSTRAINT FK_AGENT_ON_CURRENT_ADDRESS FOREIGN KEY (current_address_id) REFERENCES address (id);

ALTER TABLE agent
    ADD CONSTRAINT FK_AGENT_ON_PERMANENT_ADDRESS FOREIGN KEY (permanent_address_id) REFERENCES address (id);

ALTER TABLE agent
    ADD CONSTRAINT FK_AGENT_ON_USERID FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE company
    ADD CONSTRAINT FK_COMPANY_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_STATUS FOREIGN KEY (status_id) REFERENCES user_status (id);

ALTER TABLE admin_roles
    ADD CONSTRAINT fk_admrol_on_admin FOREIGN KEY (admin_user_id) REFERENCES `admin` (user_id);

ALTER TABLE admin_roles
    ADD CONSTRAINT fk_admrol_on_user_role FOREIGN KEY (roles_id) REFERENCES user_role (id);

ALTER TABLE agent_roles
    ADD CONSTRAINT fk_agerol_on_agent FOREIGN KEY (agent_user_id) REFERENCES agent (user_id);

ALTER TABLE agent_roles
    ADD CONSTRAINT fk_agerol_on_user_role FOREIGN KEY (roles_id) REFERENCES user_role (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user_role FOREIGN KEY (roles_id) REFERENCES user_role (id);