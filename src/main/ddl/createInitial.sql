create table DanceUser (
    id bigint primary key not null,
    Email varchar(254),
    FirstName varchar(255),
    LastName varchar(255),
    IsActive bool not null,
    PasswordHash bigint not null,
    UserType varchar(50)
);

