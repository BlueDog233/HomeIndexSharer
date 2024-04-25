
create table customindex
(
    id          int         not null
        primary key,
    update_time datetime    null,
    create_time datetime    null,
    author      varchar(50) not null,
    photo       text        null,
    `describe`  text        null,
    starcount   int         null,
    name        varchar(50) null
);

create table storeuser
(
    id         int auto_increment
        primary key,
    avatar     varchar(100) null,
    username   varchar(20)  null,
    is_publish tinyint(1)   null,
    html       text         null,
    json       text         null,
    style      text         null,
    text_data  text         null,
    photo_data text         null,
    template   int          null,
    email      varchar(30)  null,
    stars      text         null
);

create table user
(
    id          int auto_increment
        primary key,
    username    varchar(255)         not null,
    password    varchar(255)         not null,
    isbaned     tinyint(1) default 0 not null comment '0为未封禁',
    update_time datetime(6)          null,
    create_time datetime(6)          null,
    role        int        default 1 null comment '1为普通用户 2为管理员'
);

