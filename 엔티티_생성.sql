create table orders
(
    id           bigint       not null auto_increment,
    created_at   datetime     not null,
    deleted      bit          not null,
    updated_at   datetime,
    paid_at      timestamp    not null,
    product_name varchar(255) not null,
    uuid         varchar(255) not null,
    user_id      bigint       not null,
    primary key (id)
) engine=InnoDB;

create table users
(
    id           bigint       not null auto_increment,
    created_at   datetime     not null,
    deleted      bit          not null,
    updated_at   datetime,
    email        varchar(255) not null,
    gender       varchar(255),
    login_id     varchar(255) not null,
    name         varchar(255) not null,
    nickname     varchar(255) not null,
    password     varchar(255) not null,
    phone_number varchar(255) not null,
    uuid         varchar(255) not null,
    primary key (id)
) engine=InnoDB;