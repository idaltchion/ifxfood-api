insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into cozinha (id, nome) values (3, 'Japonesa')

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Gourmet', 10, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.50, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Tuk Tuk Comida Indiana', 15, 2);

insert into estado (nome) values ('Parana');
insert into estado (nome) values ('Sao Paulo');
insert into estado (nome) values ('Rio de Janeiro');
insert into estado (nome) values ('Belo Horizonte');

insert into cidade (nome, estado_id) values ('Curitiba', 1);
insert into cidade (nome, estado_id) values ('Sao Jose dos Pinhais', 1);
insert into cidade (nome, estado_id) values ('Barueri', 2);
insert into cidade (nome, estado_id) values ('Niteroi', 3);
insert into cidade (nome, estado_id) values ('Minas Gerais', 4);
insert into cidade (nome, estado_id) values ('Betim', 4);