DROP TABLE IF EXISTS account;
create table account
(
    id bigint not null
        constraint account_pkey
            primary key,
    account_number varchar(255) not null,
    balance        VARCHAR,
    name           varchar(255)
);


DROP TABLE IF EXISTS transfer;
create table transfer
(
    transfer_number bigint not null
        constraint transfer_pkey
            primary key,
    amount          VARCHAR,
    creditaccount   varchar(255),
    debitaccount    varchar(255)
);
INSERT INTO account (id, account_number, balance, name) VALUES (1,'1234', 200, 'evram');
INSERT INTO account (id, account_number, balance, name) VALUES (2,'345', 200, 'hany');