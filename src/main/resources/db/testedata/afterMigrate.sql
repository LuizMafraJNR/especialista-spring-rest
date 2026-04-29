-- Desabilita verificação de FK para limpeza dos dados
SET session_replication_role = replica;

delete from item_pedido;
delete from pedido;
delete from restaurante_forma_pagamento;
delete from usuario_grupo;
delete from grupo_permissao;
delete from produto;
delete from restaurante;
delete from forma_pagamento;
delete from grupo;
delete from permissao;
delete from usuario;
delete from cidade;
delete from estado;
delete from cozinha;
delete from restaurante_usuario_responsavel;

SET session_replication_role = DEFAULT;

-- Reinicia as sequences
ALTER SEQUENCE cozinha_id_seq RESTART WITH 1;
ALTER SEQUENCE cidade_id_seq RESTART WITH 1;
ALTER SEQUENCE estado_id_seq RESTART WITH 1;
ALTER SEQUENCE forma_pagamento_id_seq RESTART WITH 1;
ALTER SEQUENCE grupo_id_seq RESTART WITH 1;
ALTER SEQUENCE permissao_id_seq RESTART WITH 1;
ALTER SEQUENCE produto_id_seq RESTART WITH 1;
ALTER SEQUENCE restaurante_id_seq RESTART WITH 1;
ALTER SEQUENCE usuario_id_seq RESTART WITH 1;

ALTER SEQUENCE pedido_id_seq RESTART WITH 1;
ALTER SEQUENCE item_pedido_id_seq RESTART WITH 1;

insert into cozinha (id, nome) overriding system value values (1, 'Tailandesa');
insert into cozinha (id, nome) overriding system value values (2, 'Indiana');
insert into cozinha (id, nome) overriding system value values (3, 'Argentina');
insert into cozinha (id, nome) overriding system value values (4, 'Brasileira');

insert into estado (id, nome) overriding system value values (1, 'Minas Gerais');
insert into estado (id, nome) overriding system value values (2, 'São Paulo');
insert into estado (id, nome) overriding system value values (3, 'Ceará');

insert into cidade (id, nome, estado_id) overriding system value values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) overriding system value values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) overriding system value values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) overriding system value values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) overriding system value values (5, 'Fortaleza', 3);

insert into grupo (nome) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo,
                         aberto, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero,
                         endereco_bairro) overriding system value
values (1, 'Thai Gourmet', 10, 1, now() at time zone 'UTC', now() at time zone 'UTC', true, true, 1,
        '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo,
                         aberto) overriding system value
values (2, 'Thai Delivery', 9.50, 1, now() at time zone 'UTC', now() at time zone 'UTC', true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo,
                         aberto) overriding system value
values (3, 'Tuk Tuk Comida Indiana', 15, 2, now() at time zone 'UTC', now() at time zone 'UTC', true,
        true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo,
                         aberto) overriding system value
values (4, 'Java Steakhouse', 12, 3, now() at time zone 'UTC', now() at time zone 'UTC', true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo,
                         aberto) overriding system value
values (5, 'Lanchonete do Tio Sam', 11, 4, now() at time zone 'UTC', now() at time zone 'UTC', true,
        true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo,
                         aberto) overriding system value
values (6, 'Bar da Maria', 6, 4, now() at time zone 'UTC', now() at time zone 'UTC', true, true);

insert into forma_pagamento (id, descricao) overriding system value values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) overriding system value values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) overriding system value values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) overriding system value values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) overriding system value values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);

insert into usuario (id, nome, email, senha, data_cadastro) overriding system value values
    (5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', now() at time zone 'UTC');


insert into usuario (id, nome, email, senha, data_cadastro) overriding system value values
    (1, 'João da Silva', 'joao.ger@algafood.com', '123', now() at time zone 'UTC'),
    (2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', now() at time zone 'UTC'),
    (3, 'José Souza', 'jose.aux@algafood.com', '123', now() at time zone 'UTC'),
    (4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', now() at time zone 'UTC');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 5), (3, 5);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, true, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, true, 4);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 5);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, 6);



insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, subtotal, taxa_frete, valor_total) overriding system value
values (1, 'f9981ca4-5a5e-4da3-af84-6a58cc8d9a0e', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
        'CRIADO', now() at time zone 'UTC', 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) overriding system value
values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) overriding system value
values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');


insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, subtotal, taxa_frete, valor_total) overriding system value
values (2, 'b5741512-8fbc-47fa-9ac1-b530354ba0d8', 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
        'CRIADO', now() at time zone 'UTC', 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) overriding system value
values (3, 2, 6, 1, 79, 79, 'Ao ponto');

-- Atualiza as sequences para o próximo valor disponível após os inserts com IDs explícitos
SELECT setval('pedido_id_seq', (SELECT MAX(id) FROM pedido));
SELECT setval('item_pedido_id_seq', (SELECT MAX(id) FROM item_pedido));