create table users (
    id varchar(255) not null constraint user_pk primary key,
    username varchar(255) not null,
    email varchar(255) not null,
    pin int not null
);