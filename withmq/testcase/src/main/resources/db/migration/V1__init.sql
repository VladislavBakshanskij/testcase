create schema if not exists keyauto;

create type user_role as enum (
    'USER',
    'ADMIN',
    'DEVELOPER'
);

create type user_post as enum (
    'DIRECTOR',
    'TEAM_LEAD',
    'HR',
    'JUNIOR'
);

create table keyauto.auth_user (
   id serial primary key,
   name varchar not null,
   role user_role not null,
   post user_post not null,
   full_name varchar not null,
   password varchar not null
);

create table keyauto.journal (
    id serial primary key,
    login varchar not null,
    type varchar not null,
    create_date timestamptz not null,
    current_state jsonb not null
);
