alter table pedido add column codigo varchar(36) not null after id;
update pedido set codigo = uuid();
alter table pedido add constraint unique key uk_pedido_codigo (codigo);