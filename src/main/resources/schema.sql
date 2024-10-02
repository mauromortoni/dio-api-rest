drop table if exists estado;

CREATE TABLE estado (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sigla VARCHAR(2) NOT NULL
);

drop table if exists cidade;

CREATE TABLE cidade (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    capital BOOLEAN,
    estado_id BIGINT NOT NULL,
    FOREIGN KEY (estado_id) REFERENCES estado(id)
);
