CREATE TABLE psicologos(
    id_psicologo INT not null auto_increment,
    id_coordenacao INT not null,
    Nome VARCHAR(255) NOT NULL,
    Matricula VARCHAR(255) NOT NULL,
    Ativo BOOLEAN DEFAULT TRUE,
    primary key (id_psicologo),
    FOREIGN KEY (id_coordenacao) REFERENCES coordenacao(id)
);