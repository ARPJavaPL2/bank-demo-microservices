create table if not exists customer
(
    id      bigint auto_increment primary key,
    name    varchar(50)  not null,
    pesel   varchar(13)  not null,
    surname varchar(100) not null
);

INSERT INTO customer (id, name, surname, pesel)
VALUES (3, 'Mark', 'Doe', '90083078213');