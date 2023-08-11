create table holiday
(
    id   bigint       not null auto_increment,
    date date         not null,
    name varchar(255) not null,
    primary key (id)
)
