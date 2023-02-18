create table client
(
    id       BIGSERIAL PRIMARY KEY,
    nickname text not null
);


create table timeLine
(
    id        BIGSERIAL PRIMARY KEY,
    startAt   timestamp default now(),
    activity  text   not null,
    comment   text   not null,
    client_id bigint not null
);