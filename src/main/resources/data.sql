DROP TABLE IF EXISTS account;
create table account
(
    account_number varchar(255) not null
        constraint account_pkey
            primary key,
    balance        bigint,
    name           varchar(255)
);


DROP TABLE IF EXISTS transfer;
create table transfer
(
    transfer_number bigint not null
        constraint transfer_pkey
            primary key,
    amount          bigint,
    creditaccount   varchar(255),
    debitaccount    varchar(255)
);
INSERT INTO account (account_number, balance, name) VALUES ('1234', 200, 'evram');
INSERT INTO account (account_number, balance, name) VALUES ('345', 200, 'hany');