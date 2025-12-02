CREATE TABLE sessao(
                         id_sessao INT not null AUTO_INCREMENT,
                         id_coordenacao INT NOT NULL,
                         id_psicologo INT NOT NULL,
                         id_paciente INT NOT NULL,
                         Data_Hora DATETIME NOT NULL,
                         Status ENUM('PENDENTE_APROVACAO', 'APROVADA', 'REALIZADA', 'CANCELADA') NOT NULL,
                         Data_Aprovacao DATETIME,
                         Observacoes TEXT,

                         PRIMARY KEY (id_sessao),
                         FOREIGN KEY (id_coordenacao) REFERENCES coordenacao(id),
                         FOREIGN KEY (id_psicologo) REFERENCES psicologos(id),
                         FOREIGN KEY (id_paciente) REFERENCES pacientes(id)
);