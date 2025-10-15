CREATE TABLE book(
                     id uuid not null primary key,
                     title varchar(100) not null,
                     author varchar(50) not null,
                     publication_date date,
                     loan bool NOT NULL DEFAULT false
);

CREATE TABLE bs_user(
                        id uuid not null primary key,
                        user_name varchar(100) not null,
                        email varchar(50) not null
);