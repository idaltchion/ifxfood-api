set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from restaurante_usuario_responsavel;
delete from usuario;
delete from usuario_grupo;
delete from pedido;
delete from item_pedido;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;

insert into estado (id, nome) values (1, 'Parana');
insert into estado (id, nome) values (2, 'Sao Paulo');
insert into estado (id, nome) values (3, 'Rio de Janeiro');
insert into estado (id, nome) values (4, 'Belo Horizonte');


insert into cidade (id, nome, estado_id) values (1, 'Curitiba', 1);
insert into cidade (id, nome, estado_id) values (2, 'Sao Jose dos Pinhais', 1);
insert into cidade (id, nome, estado_id) values (3, 'Barueri', 2);
insert into cidade (id, nome, estado_id) values (4, 'Niteroi', 3);
insert into cidade (id, nome, estado_id) values (5, 'Minas Gerais', 4);
insert into cidade (id, nome, estado_id) values (6, 'Betim', 4);


insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira');
insert into cozinha (id, nome) values (99, 'Japonesa');


insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, 0, "83050490", "Rua Hercules Lopes", "405", "", "Afonso Pena", 2);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, 1, 0);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, 1, 0);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, 1, 0);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, 1, 0);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, 1, 0);


insert into forma_pagamento (id, descricao) values (1, 'Dinheiro');
insert into forma_pagamento (id, descricao) values (2, 'Cartao de Credito');
insert into forma_pagamento (id, descricao) values (3, 'Cartao de Debito');


insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 1), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);


insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);


insert into grupo (id, nome) values (1, 'Vendedores');
insert into grupo (id, nome) values (2, 'Entregadores');
insert into grupo (id, nome) values (3, 'Desenvolvedores');

insert into permissao (id, nome, descricao) values (1, 'EDITAR_COZINHA', 'Permite editar cozinhas existentes');
insert into permissao (id, nome, descricao) values (2, 'CONSULTAR_COZINHA', 'Permite consultar cozinhas existentes');
insert into permissao (id, nome, descricao) values (3, 'EXCLUIR_COZINHAS', 'Permite excluir cozinhas existentes');

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1);
insert into grupo_permissao (grupo_id, permissao_id) values (1, 3);
insert into grupo_permissao (grupo_id, permissao_id) values (2, 2);
insert into grupo_permissao (grupo_id, permissao_id) values (3, 2);



insert into usuario (id, nome, email, senha, data_cadastro) values (1, "Joao Nabuco", "nabuco@ifxfood.com", "123", utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (2, "Maria Joaquina", "joaquina@ifxfood.com", "123", utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (3, "Scarlett Johansson", "scarlett@ifxfood.com", "123", utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (4, "Philip de Moraes", "philip@ifxfood.com", "123", utc_timestamp);

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 1), (3, 2), (3, 3), (3, 1);



insert into pedido (
	id, codigo, subtotal, taxa_frete, valor_total, status, 
	data_criacao, data_confirmacao, data_cancelamento, data_entrega, 
	endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, 
	restaurante_id, usuario_cliente_id, forma_pagamento_id) 
	values (
	1, '8b9e7c80-46d1-4a35-b520-196c56823b22', 374.5, 25.5, 400, 'CRIADO',
	'2021-06-04 00:01:36', utc_timestamp, utc_timestamp, utc_timestamp,
	'22625-081', 'Rua das Araras', '57', 'em frente ao posto de saúde', 'Bairro da Saude', 1, 
	1, 1, 1);

insert into item_pedido (id, quantidade, preco_unitario, preco_total, observacao, pedido_id, produto_id) 
	values (1, 2, 25.9, 51.8, 'sem molho', 1, 8);
	
insert into item_pedido (id, quantidade, preco_unitario, preco_total, observacao, pedido_id, produto_id) 
	values (2, 1, 110.0, 110.0, 'sem observacao', 1, 1);


insert into pedido (
	id, codigo, subtotal, taxa_frete, valor_total, status, 
	data_criacao, data_confirmacao, data_cancelamento, data_entrega, 
	endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, 
	restaurante_id, usuario_cliente_id, forma_pagamento_id) 
	values (
	2, 'f9b40634-016f-44c1-aa22-ecc22729f6ca', 30, 7, 37, 'ENTREGUE',
	'2021-06-03 01:31:21', '2021-06-03 01:45:31', null, '2021-06-03 02:11:27',
	'80654-050', 'Av. Rui Barbosa', '22', 'em frente a floricultura', 'Afonso Pena', 2, 
	3, 4, 2);

insert into item_pedido (id, quantidade, preco_unitario, preco_total, observacao, pedido_id, produto_id) 
	values (3, 2, 15, 30, '', 2, 3);
	
insert into item_pedido (id, quantidade, preco_unitario, preco_total, observacao, pedido_id, produto_id) 
	values (4, 3, 60, 118, 'molho separado', 2, 2);


insert into pedido (
	id, codigo, subtotal, taxa_frete, valor_total, status, 
	data_criacao, data_confirmacao, data_cancelamento, data_entrega, 
	endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, 
	restaurante_id, usuario_cliente_id, forma_pagamento_id) 
	values (
	4, uuid(), 30, 7, 37, 'CONFIRMADO',
	'2021-06-02 23:31:21', '2021-06-02 23:47:11', null, '2021-06-02 23:59:21',
	'45567-150', 'Rua Bambino', '100', null, 'Portao Alegre', 4, 
	6, 3, 1);

insert into item_pedido (id, quantidade, preco_unitario, preco_total, observacao, pedido_id, produto_id) 
	values (5, 1, 35, 35, '', 4, 9);
	
insert into item_pedido (id, quantidade, preco_unitario, preco_total, observacao, pedido_id, produto_id) 
	values (6, 3, 60, 118, 'molho separado', 4, 2);
	
	
insert into pedido (
	id, codigo, subtotal, taxa_frete, valor_total, status, 
	data_criacao, data_confirmacao, data_cancelamento, data_entrega, 
	endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, 
	restaurante_id, usuario_cliente_id, forma_pagamento_id) 
	values (
	6, uuid(), 30, 7, 37, 'CONFIRMADO',
	'2021-05-27 15:23:12', '2021-05-27 15:36:52', null, null,
	'80654-050', 'Av. Rui Barbosa', '22', 'em frente a floricultura', 'Afonso Pena', 2, 
	2, 4, 2);

insert into item_pedido (id, quantidade, preco_unitario, preco_total, observacao, pedido_id, produto_id) 
	values (7, 2, 15, 30, '', 6, 5);
	
	
insert into pedido (
	id, codigo, subtotal, taxa_frete, valor_total, status, 
	data_criacao, data_confirmacao, data_cancelamento, data_entrega, 
	endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, 
	restaurante_id, usuario_cliente_id, forma_pagamento_id) 
	values (
	8, uuid(), 30, 7, 37, 'CANCELADO',
	'2021-05-29 17:32:18', null, '2021-05-29 18:22:59', null,
	'80654-050', 'Av. Rui Barbosa', '22', 'em frente a floricultura', 'Afonso Pena', 2, 
	4, 4, 2);
	
insert into item_pedido (id, quantidade, preco_unitario, preco_total, observacao, pedido_id, produto_id) 
	values (8, 3, 60, 118, 'molho separado', 8, 6);


