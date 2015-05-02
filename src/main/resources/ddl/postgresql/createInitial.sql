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

drop table dance_user;
create table dance_user (
    user_id uuid primary key not null,
    email varchar(254) not null,
    first_name varchar(255),
    last_name varchar(255),
    is_active bool not null,
    user_type varchar(50)
);
create index dance_user_index_name on dance_user (last_name, first_name);

drop table dance_user_roles;
create table dance_user_roles (
    user_id uuid not null,
    role_id int not null,
    primary key (user_id, role_id)
);

drop table aggregate_passwords;
create table aggregate_passwords (
    aggregate_id uuid primary key not null,
    password_hash bytea not null,
    salt bytea not null
);

