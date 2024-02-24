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
    id BIGSERIAL PRIMARY KEY,
    user_id varchar(10) not null,
    ticket_id BIGSERIAL not null ,
    FOREIGN KEY(ticket_id) references lottery(ticket_id)
);

