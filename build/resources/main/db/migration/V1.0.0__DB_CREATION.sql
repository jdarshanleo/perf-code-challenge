create table upgrade_user (
    id uuid not null primary key,
    create_date date not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null
);
