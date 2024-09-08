--liquibase formatted sql

--changeset am:1

create table card
(
    id             BIGSERIAL PRIMARY KEY,
    balance        FLOAT,
    credit_balance FLOAT,
    cash_back      FLOAT,
    bonus          FLOAT,
    all_balance    TEXT,
    date_time      TIMESTAMP
);

create table users
(
    id            BIGSERIAL PRIMARY KEY,
    first_name    VARCHAR(20),
    last_name     VARCHAR(20),
    middle_name   VARCHAR(20),
    phone_number  VARCHAR(11),
    date_of_birth DATE,
    email         VARCHAR(20),
    password      VARCHAR(200),
    role          SMALLINT,
    card_id       BIGINT REFERENCES card (id)
);





