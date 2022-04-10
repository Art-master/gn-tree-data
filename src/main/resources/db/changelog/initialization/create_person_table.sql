--liquibase formatted sql

--changeset Art-_-master:create_person_table_09.04.2022

create table if not exists person
(
    id                  bigserial not null primary key,
    name                varchar   not null,
    "first_name"        varchar   not null,
    "last_name"         varchar   not null
);

alter table person
    owner to postgres;