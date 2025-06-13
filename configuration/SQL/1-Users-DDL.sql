--Create Start
DROP SCHEMA IF EXISTS home_banking CASCADE;
CREATE SCHEMA home_banking;

CREATE TABLE home_banking.users (
    ID SERIAL PRIMARY KEY NOT NULL,
    USERNAME VARCHAR(100) NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    ENABLED bool NOT NULL DEFAULT false,
    ROLE VARCHAR(100)
);

create table home_banking.accounts (
    ID SERIAL PRIMARY KEY not null,
    CREATE_DATE timestamp,
    NUMBER varchar(255),
    USER_ID varchar(255)
);

insert into home_banking.users (id, username, password, email, enabled, role)
values (1, 'seba','password123','seba@seba.com',true, 'USER_ADMIN'),
       (2, 'natalia','password123','natalia@natalia.com',true, 'USER_ADMIN'),
       (3, 'isabella','password123','isabella@isabella.com',true, 'AUTHOR_ADMIN'),
       (4, 'ignacio','password123','ignacio@ignacio.com',true, 'BOOK_ADMIN');

insert into home_banking.accounts (id, create_date, number, user_id)
values (1, now(),'12345678', '1'),
       (2, now(),'87654321', '2'),
       (3, now(),'12341234', '3'),
       (4, now(),'56785678', '4');