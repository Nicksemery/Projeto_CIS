CREATE TABLE pacientes(
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          id_coordenacao INT NOT NULL,
                          nome VARCHAR(255) NOT NULL,
                          telefone VARCHAR(20) UNIQUE NOT NULL,
                          disponibilidade_data ENUM('SEGUNDA','TERCA','QUARTA', 'QUINTA', 'SEXTA', 'SABADO'),
                          disponibilidade_horario ENUM('H0800','H0900','H1000','H1100','H1200','H1300','H1400','H1500','H1600','H1700','H1800','H1900','H2000') NOT NULL, -- Ajustado para o Enum Java
                          data_registro DATE NOT NULL,
                          ativo BOOLEAN default true,
                          FOREIGN KEY (id_coordenacao) REFERENCES coordenacao(id)
);