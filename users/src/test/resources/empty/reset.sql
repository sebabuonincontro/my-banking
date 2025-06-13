CREATE TABLE IF NOT EXISTS home_banking.users (
    ID SERIAL PRIMARY KEY NOT NULL,
    USERNAME VARCHAR(100) NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    ENABLED bool NOT NULL DEFAULT false,
    ROLE VARCHAR(100)
);

create table IF NOT EXISTS home_banking.accounts (
    ID SERIAL PRIMARY KEY not null,
    CREATE_DATE timestamp,
    NUMBER varchar(255),
    USER_ID varchar(255)
);