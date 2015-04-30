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
create unique index dance_user_index_email on dance_user (email);

drop table dance_user_roles;
create table dance_user_roles (
    user_id uuid not null,
    role_id int not null,
    primary key (user_id, role_id)
);

drop table aggregate_passwords;
create table aggregate_passwords (
    aggregate_id uuid primary key not null,
    password_hash binary(512) not null,
    salt binary(512) not null
);

