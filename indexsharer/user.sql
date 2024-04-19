create table user
(
    id          int auto_increment
        primary key,
    username    varchar(255)         not null,
    password    varchar(255)         not null,
    html        varchar(255)         null,
    mail        varchar(255)         null,
    avatar      varchar(255)         null,
    ispublished tinyint(1) default 0 not null comment '0为未发布',
    isbaned     tinyint(1) default 0 not null comment '0为未封禁',
    update_time datetime(6)          null,
    create_time datetime(6)          null
);

