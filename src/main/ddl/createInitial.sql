drop table DanceUser;
create table DanceUser (
    userId uuid primary key not null,
    Email varchar(254) not null,
    FirstName varchar(255),
    LastName varchar(255),
    IsActive bool not null,
    PasswordHash bigint not null,
    UserType varchar(50)
);
create index DanceUser_Name on DanceUser (LastName, FirstName);

drop table DanceUserRoles;
create table DanceUserRoles (
    userId uuid not null,
    roleId int not null,
    primary key (userId, roleId)
);

