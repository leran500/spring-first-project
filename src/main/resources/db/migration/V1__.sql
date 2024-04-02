CREATE TABLE messages
(
    id         VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    balance    INT NULL,
    CONSTRAINT pk_messages PRIMARY KEY (id)
);