CREATE TABLE pacientes(
                          id_paciente INT PRIMARY KEY AUTO_INCREMENT,
                          id_coordenacao INT NOT NULL,
                          Nome VARCHAR(255) NOT NULL,
                          Telefone VARCHAR(20) UNIQUE NOT NULL,
                          Disponibilidade_Data ENUM('SEGUNDA','TERCA','QUARTA', 'QUINTA', 'SEXTA', 'SABADO'),
                          Disponibilidade_Horario ENUM('H0800','H0900','H1000','H1100','H1200','H1300','H1400','H1500','H1600','H1700','H1800','H1900','H2000') NOT NULL, -- Ajustado para o Enum Java
                          Data_Registro DATE NOT NULL,
                          id_usuario INT UNIQUE NOT NULL,
                          FOREIGN KEY (id_coordenacao) REFERENCES coordenacao(id),
                          FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);