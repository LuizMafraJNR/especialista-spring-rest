create table if not exists estado (
    id bigint not null generated always as identity,
    nome varchar(80) not null,
    primary key (id)
);

insert into estado (nome) select distinct nome_estado from cidade;

alter table cidade add column estado_id bigint not null default 0;

update cidade set estado_id = (select e.id from estado e where e.nome = cidade.nome_estado);

alter table cidade alter column estado_id drop default;

alter table cidade add constraint fk_cidade_estado
    foreign key (estado_id) references estado (id);

alter table cidade drop column nome_estado;

alter table cidade rename column nome_cidade to nome;
alter table cidade alter column nome type varchar(80);
