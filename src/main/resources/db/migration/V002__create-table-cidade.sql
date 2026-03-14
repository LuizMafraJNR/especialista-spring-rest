create table cidade (
    id bigint not null generated always as identity,
    nome_cidade varchar(80) not null,
    nome_estado varchar(80) not null,
    primary key (id)
);
