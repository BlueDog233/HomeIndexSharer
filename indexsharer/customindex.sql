create table customindex
(
    id          int          not null
        primary key,
    update_time datetime     null,
    create_time datetime     null,
    username    varchar(255) not null,
    response    text         null
);

