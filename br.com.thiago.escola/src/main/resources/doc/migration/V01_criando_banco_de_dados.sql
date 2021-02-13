BANCO POSTGRESQL VERSÃO 9.2

CREATE DATABASE bdescola
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'C'
       LC_CTYPE = 'C'
       CONNECTION LIMIT = -1;

CREATE TABLE aluno(
  codigo bigint NOT NULL,
  cpf character varying(11) NOT NULL,
  email character varying(90) NOT NULL,
  nome character varying(90) NOT NULL,
  CONSTRAINT aluno_pkey PRIMARY KEY (codigo))
WITH (OIDS=FALSE);

ALTER TABLE aluno
  OWNER TO postgres;

CREATE TABLE materia(
  codigo bigint NOT NULL,
  descricao character varying(90) NOT NULL,
  CONSTRAINT materia_pkey PRIMARY KEY (codigo))
WITH (OIDS=FALSE);

ALTER TABLE materia
  OWNER TO postgres;

CREATE TABLE professor(
  codigo bigint NOT NULL,
  cpf character varying(11) NOT NULL,
  email character varying(90) NOT NULL,
  nome character varying(90) NOT NULL,
  salario numeric(10,2) NOT NULL,
  CONSTRAINT professor_pkey PRIMARY KEY (codigo))
WITH (OIDS=FALSE);

ALTER TABLE professor
  OWNER TO postgres;

CREATE TABLE lancamento(
  codigo bigint NOT NULL,
  media character varying(255) NOT NULL,
  nota1 numeric(10,2) NOT NULL,
  nota2 numeric(10,2) NOT NULL,
  nota3 numeric(10,2) NOT NULL,
  codigo_aluno bigint NOT NULL,
  codigo_materia bigint NOT NULL,
  codigo_professor bigint NOT NULL,
  CONSTRAINT lancamento_pkey PRIMARY KEY (codigo),
  CONSTRAINT fk_aluno FOREIGN KEY (codigo_aluno)
      REFERENCES aluno (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_materia FOREIGN KEY (codigo_materia)
      REFERENCES materia (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_professor FOREIGN KEY (codigo_professor)
      REFERENCES professor (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION)
WITH (OIDS=FALSE);

ALTER TABLE lancamento
  OWNER TO postgres;

CREATE SEQUENCE aluno_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

ALTER TABLE aluno_seq
  OWNER TO postgres;


CREATE SEQUENCE materia_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE materia_seq
  OWNER TO postgres;

CREATE SEQUENCE professor_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE professor_seq
  OWNER TO postgres;

CREATE SEQUENCE lancamento_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE lancamento_seq
  OWNER TO postgres;