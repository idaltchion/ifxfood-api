insert ignore into  estado (nome) values ('Parana');
insert ignore into  estado (nome) values ('Sao Paulo');
insert ignore into  estado (nome) values ('Rio de Janeiro');
insert ignore into  estado (nome) values ('Belo Horizonte');


insert ignore into  cidade (nome, estado_id) values ('Curitiba', 1);
insert ignore into  cidade (nome, estado_id) values ('Sao Jose dos Pinhais', 1);
insert ignore into  cidade (nome, estado_id) values ('Barueri', 2);
insert ignore into  cidade (nome, estado_id) values ('Niteroi', 3);
insert ignore into  cidade (nome, estado_id) values ('Minas Gerais', 4);
insert ignore into  cidade (nome, estado_id) values ('Betim', 4);


insert ignore into  cozinha (id, nome) values (1, 'Tailandesa');
insert ignore into  cozinha (id, nome) values (2, 'Indiana');
insert ignore into  cozinha (id, nome) values (3, 'Argentina');
insert ignore into  cozinha (id, nome) values (4, 'Brasileira');
insert ignore into  cozinha (id, nome) values (99, 'Japonesa');


insert ignore into  restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values ('Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, "83-050-490", "Rua Hercules Lopes", "405", "", "Afonso Pena", 2);
insert ignore into  restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert ignore into  restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);
insert ignore into  restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
insert ignore into  restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp);
insert ignore into  restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp);


insert ignore into  forma_pagamento (id, descricao) values (1, 'Dinheiro');
insert ignore into  forma_pagamento (id, descricao) values (2, 'Cartao de Credito');
insert ignore into  forma_pagamento (id, descricao) values (3, 'Cartao de Debito');


insert ignore into  restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 1), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);


insert ignore into  produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert ignore into  produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert ignore into  produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert ignore into  produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert ignore into  produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert ignore into  produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert ignore into  produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert ignore into  produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert ignore into  produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);


