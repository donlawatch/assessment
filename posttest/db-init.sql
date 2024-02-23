DROP TABLE IF EXISTS lottery, user_ticket CASCADE;

CREATE TABLE lottery
(
    ticket_id BIGSERIAL PRIMARY KEY,
    ticket varchar(6) not null ,
    price float not null ,
    amount int not null

);

CREATE TABLE user_ticket
(
    userID varchar(10) not null
);

