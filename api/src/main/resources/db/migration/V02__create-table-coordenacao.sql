CREATE TABLE coordenacao(
                            id INT not null auto_increment,
                            nome VARCHAR(255) NOT NULL,
                            email varchar(255),
                            matricula VARCHAR(255) NOT NULL,
                            ativo BOOLEAN DEFAULT TRUE,
                            UNIQUE(matricula),
                            primary key (id)
);