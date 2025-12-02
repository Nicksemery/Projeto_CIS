CREATE TABLE psicologos(
    id INT not null auto_increment,
    id_coordenacao INT not null,
    nome VARCHAR(255) NOT NULL,
    matricula VARCHAR(255) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    primary key (id),
    FOREIGN KEY (id_coordenacao) REFERENCES coordenacao(id)
);