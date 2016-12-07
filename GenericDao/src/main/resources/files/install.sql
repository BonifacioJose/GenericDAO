DROP SEQUENCE IF EXISTS cliente_id_seq;
DROP TABLE IF EXISTS cliente;

CREATE SEQUENCE cliente_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE cliente (
	id integer not null DEFAULT nextval('cliente_id_seq'::regclass),
	nome character varying(255) not null,
	email character varying(255),
	ativo boolean,
    CONSTRAINT cliente_pk PRIMARY KEY (id)
);
