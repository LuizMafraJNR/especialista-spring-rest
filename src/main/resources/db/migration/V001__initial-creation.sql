create table cozinha (
                         id bigint not null generated always as identity,
                         nome varchar(60) not null,
                         primary key (id)
);
