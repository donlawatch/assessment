DROP TABLE IF EXISTS lottery, user_ticket CASCADE;

CREATE TABLE lottery
(
    ticket varchar(6) PRIMARY KEY ,
    price float not null ,
    amount int not null

);

CREATE TABLE user_ticket
(
    id BIGSERIAL PRIMARY KEY,
    user_id varchar(10) not null,
    ticket varchar(6) not null ,
    FOREIGN KEY(ticket) references lottery(ticket)
);

