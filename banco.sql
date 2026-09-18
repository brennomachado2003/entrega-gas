CREATE DATABASE empresa;
CREATE DATABASE entregador;
CREATE DATABASE estoque;
CREATE DATABASE logistica;
CREATE DATABASE notificacao;
CREATE DATABASE pedidos;
CREATE DATABASE produtos;
CREATE DATABASE usuario;


\connect empresa
CREATE EXTENSION IF NOT EXISTS postgis;


\connect entregador
CREATE EXTENSION IF NOT EXISTS postgis;


\connect logistica
CREATE EXTENSION IF NOT EXISTS postgis;


\connect usuario
CREATE EXTENSION IF NOT EXISTS postgis;