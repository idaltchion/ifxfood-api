insert ignore into estado (id, nome) values (1, 'Parana');
insert ignore into estado (id, nome) values (2, 'Sao Paulo');
insert ignore into estado (id, nome) values (3, 'Rio de Janeiro');
insert ignore into estado (id, nome) values (4, 'Belo Horizonte');


insert ignore into cidade (id, nome, estado_id) values (1, 'Curitiba', 1);
insert ignore into cidade (id, nome, estado_id) values (2, 'Sao Jose dos Pinhais', 1);
insert ignore into cidade (id, nome, estado_id) values (3, 'Barueri', 2);
insert ignore into cidade (id, nome, estado_id) values (4, 'Niteroi', 3);
insert ignore into cidade (id, nome, estado_id) values (5, 'Minas Gerais', 4);
insert ignore into cidade (id, nome, estado_id) values (6, 'Betim', 4);


insert ignore into cozinha (id, nome) values (1, 'Tailandesa');
insert ignore into cozinha (id, nome) values (2, 'Indiana');
insert ignore into cozinha (id, nome) values (3, 'Argentina');
insert ignore into cozinha (id, nome) values (4, 'Brasileira');
insert ignore into cozinha (id, nome) values (99, 'Japonesa');


insert ignore into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, "83-050-490", "Rua Hercules Lopes", "405", "", "Afonso Pena", 2);
insert ignore into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, 1);
insert ignore into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, 1);
insert ignore into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, 1);
insert ignore into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, 1);
insert ignore into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, 1);


insert ignore into forma_pagamento (id, descricao) values (1, 'Dinheiro');
insert ignore into forma_pagamento (id, descricao) values (2, 'Cartao de Credito');
insert ignore into forma_pagamento (id, descricao) values (3, 'Cartao de Debito');


insert ignore into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 1), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);


insert ignore into produto (id, nome, descricao, preco, ativo, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert ignore into produto (id, nome, descricao, preco, ativo, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert ignore into produto (id, nome, descricao, preco, ativo, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert ignore into produto (id, nome, descricao, preco, ativo, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert ignore into produto (id, nome, descricao, preco, ativo, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert ignore into produto (id, nome, descricao, preco, ativo, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert ignore into produto (id, nome, descricao, preco, ativo, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert ignore into produto (id, nome, descricao, preco, ativo, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert ignore into produto (id, nome, descricao, preco, ativo, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);


insert ignore into grupo (id, nome) values (1, 'Vendedores');
insert ignore into grupo (id, nome) values (2, 'Entregadores');


insert ignore into usuario (id, nome, email, senha, data_cadastro) values (1, "Joao Nabuco", "nabuco@ifxfood.com", "123", utc_timestamp);
insert ignore into usuario (id, nome, email, senha, data_cadastro) values (2, "Maria Joaquina", "joaquina@ifxfood.com", "123", utc_timestamp);
insert ignore into usuario (id, nome, email, senha, data_cadastro) values (3, "Scarlett Johansson", "scarlett@ifxfood.com", "123", utc_timestamp);
insert ignore into usuario (id, nome, email, senha, data_cadastro) values (4, "Philip de Moraes", "philip@ifxfood.com", "123", utc_timestamp);


