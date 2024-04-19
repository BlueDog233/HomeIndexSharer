create table template1
(
    id          int auto_increment
        primary key,
    username    varchar(255) not null,
    title       text         null comment '传入5个长度的数组 json',
    avatar      varchar(255) null,
    info        text         null comment 'HashMap,json',
    label       text         null,
    nav         text         null comment 'List<HashMap>',
    href        varchar(255) null comment '博客链接',
    say         varchar(255) null comment 'HashMap,json',
    works       text         null comment 'List<HashMap>',
    skills      text         null comment 'List<HashMap>',
    photos      text         null comment 'List<HashMap>',
    update_time datetime     null,
    create_time datetime     null
);

