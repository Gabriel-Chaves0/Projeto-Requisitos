-- Criar tabela Abrigo
CREATE TABLE abrigo (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome_abrigo VARCHAR(255) NOT NULL,
                        capacidade_abrigo INT NOT NULL,
                        endereco VARCHAR(255),
                        contato VARCHAR(255)
);

-- Criar tabela Pessoa
CREATE TABLE pessoa (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(255) NOT NULL,
                        cpf VARCHAR(14) NOT NULL UNIQUE,
                        email VARCHAR(255),
                        telefone VARCHAR(15),
                        abrigo_id BIGINT,
                        FOREIGN KEY (abrigo_id) REFERENCES abrigo(id)
);

-- Criar tabela Animal
CREATE TABLE animal (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome_animal VARCHAR(255) NOT NULL,
                        idade_animal VARCHAR(50),
                        especie VARCHAR(50),
                        raca VARCHAR(50),
                        porte VARCHAR(50),
                        sexo VARCHAR(50),
                        adotado BOOLEAN,
                        abrigo_id BIGINT,
                        FOREIGN KEY (abrigo_id) REFERENCES abrigo(id)
);

-- Criar tabela Voluntario
CREATE TABLE voluntario (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            pessoa_id BIGINT,
                            abrigo_id BIGINT,
                            FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),
                            FOREIGN KEY (abrigo_id) REFERENCES abrigo(id)
);
