CREATE TABLE coordenacao(
                            id INT not null auto_increment,
                            Nome VARCHAR(255) NOT NULL,
                            email varchar(255),
                            Matricula VARCHAR(255) NOT NULL,
                            Ativo BOOLEAN DEFAULT TRUE,
                            UNIQUE(Matricula),
                            primary key (id)
);