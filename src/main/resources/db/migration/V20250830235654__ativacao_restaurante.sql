alter table restaurante add ativo tinyint(1) not null default 1;
update restaurante set ativo = true;