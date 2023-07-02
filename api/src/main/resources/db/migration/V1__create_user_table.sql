create table user
(
    id        bigint       not null auto_increment,
    birthday  date         not null,
    email     varchar(255) not null,
    name      varchar(255) not null,
    password  varchar(255) not null,
    phone     varchar(11)  not null,
    user_type varchar(255),
    primary key (id)
);
