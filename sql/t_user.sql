create database book;

create table t_user(
    id int primary key auto_increment,
    username varchar(10) not null unique,
    password varchar(32) not null,
    email varchar(20)
);