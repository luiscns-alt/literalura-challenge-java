-- V1__Create_tables.sql

CREATE TABLE autor
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(255) NOT NULL,
    ano_nascimento  INT,
    ano_falecimento INT
);

CREATE TABLE livro
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo           VARCHAR(255) NOT NULL,
    idioma           VARCHAR(50),
    numero_downloads INT,
    autor_id         BIGINT,
    FOREIGN KEY (autor_id) REFERENCES autor (id)
);