/*drop table posts cascade;
drop table follows cascade;
drop table file_info cascade;
drop table users cascade;
*/
create table users
(
    id            serial
        constraint users_pkey
            primary key,
    first_name    varchar(20),
    last_name     varchar(20),
    age           integer,
    password_hash varchar(100) not null,
    email         varchar(100) not null
        constraint users_email_key
            unique,
    avatar_id     integer
        constraint users_avatar_id_fkey
            references file_info
);

create table follows
(
    profile_id   integer not null references users (id),
    following_id integer not null references users (id)
);

create table posts
(
    id         serial
        constraint posts_pkey
            primary key,
    author_id  integer       not null
        constraint posts_author_id_fkey
            references users,
    created_at timestamp     not null,
    content    varchar(1000) not null
);

create table file_info
(
    id                 serial
        constraint file_info_pkey
            primary key,
    original_file_name varchar(100),
    storage_file_name  varchar(100) not null,
    size               bigint       not null,
    type               varchar(100)
);