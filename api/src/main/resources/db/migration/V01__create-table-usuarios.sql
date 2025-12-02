CREATE TABLE usuarios(
                         id_usuario INT PRIMARY KEY AUTO_INCREMENT,
                         Login VARCHAR(255) NOT NULL UNIQUE,
                         Senha VARCHAR(255) NOT NULL,
                         Permissao ENUM('COORDENACAO','PSICOLOGO', 'PACIENTE') NOT NULL
);