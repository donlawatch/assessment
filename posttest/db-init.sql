DROP TABLE IF EXISTS lottery, user_ticket CASCADE;

CREATE TABLE lottery
(
    ticket_id BIGSERIAL PRIMARY KEY,
    ticket varchar(6) not null ,
    price int not null ,
    amount int not null

);

CREATE TABLE user_ticket
(
    userID varchar(6) not null
);


INSERT INTO lottery(ticket, price, amount)
VALUES ('123456', 50, 1);

INSERT INTO lottery(ticket, price, amount)
VALUES ('123457', 60, 2);

