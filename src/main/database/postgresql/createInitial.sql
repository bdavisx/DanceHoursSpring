-- drop table domainevententry;
-- create table domainevententry
-- (
--     aggregateidentifier uuid not null,
--     sequencenumber bigint not null,
--     type varchar(255) not null,
--     eventidentifier varchar(255) not null,
--     metadata bytea,
--     payload bytea not null,
--     payloadrevision varchar(255),
--     payloadtype varchar(255) not null,
--     timestamp varchar(255) not null,
--     primary key (aggregateidentifier, sequencenumber, type)
-- );

CREATE DATABASE dance_hours
WITH ENCODING ='UTF8'
OWNER =dance
CONNECTION LIMIT =-1;

DROP TABLE dance_user;
CREATE TABLE dance_user (
    user_id    UUID PRIMARY KEY NOT NULL,
    email      VARCHAR(254)     NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    is_active  BOOL             NOT NULL
);
CREATE INDEX dance_user_index_name ON dance_user (last_name, first_name);

DROP TABLE dance_user_roles;
CREATE TABLE dance_user_roles (
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    PRIMARY KEY (user_id, role_id)
);

DROP TABLE user_roles;
CREATE TABLE user_roles (
    role_id     UUID PRIMARY KEY NOT NULL,
    name        VARCHAR(255),
    description TEXT,
    is_admin    BOOL
);

DROP TABLE aggregate_passwords;
CREATE TABLE aggregate_passwords (
    aggregate_id  UUID PRIMARY KEY NOT NULL,
    password_hash BYTEA            NOT NULL,
    salt          BYTEA            NOT NULL
);

