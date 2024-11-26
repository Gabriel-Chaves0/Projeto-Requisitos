INSERT INTO abrigo (nome_abrigo, capacidade_abrigo, endereco, contato) VALUES
                                                                           ('Abrigo Esperança', 50, 'Rua 1, Recife', 'esperanca@example.com'),
                                                                           ('Abrigo São Francisco', 30, 'Rua 2, São Paulo', 'francisco@example.com'),
                                                                           ('Abrigo dos Animais', 40, 'Rua 3, Rio de Janeiro', 'animais@example.com'),
                                                                           ('Abrigo Amigos dos Bichos', 25, 'Rua 4, Salvador', 'amigos@example.com');

INSERT INTO pessoa (nome, cpf, email, telefone, abrigo_id) VALUES
                                                               ('Maria Silva', '12345678901', 'maria@example.com', '81912345678', 1),
                                                               ('João Souza', '98765432100', 'joao@example.com', '81987654321', 2),
                                                               ('Ana Pereira', '11122233344', 'ana@example.com', '81934567890', 3),
                                                               ('Carlos Oliveira', '55566677788', 'carlos@example.com', '81956789012', 4);

INSERT INTO animal (nome_animal, idade_animal, especie, raca, porte, sexo, adotado, abrigo_id) VALUES
                                                                                                   ('Rex', '2 anos', 'Cachorro', 'Labrador', 'Grande', 'Macho', false, 1),
                                                                                                   ('Mia', '1 ano', 'Gato', 'Siamês', 'Pequeno', 'Fêmea', true, 1),
                                                                                                   ('Thor', '3 anos', 'Cachorro', 'Pitbull', 'Grande', 'Macho', false, 2),
                                                                                                   ('Luna', '6 meses', 'Gato', 'Persa', 'Pequeno', 'Fêmea', false, 2),
                                                                                                   ('Bobby', '4 anos', 'Cachorro', 'Vira-lata', 'Médio', 'Macho', true, 3),
                                                                                                   ('Fiona', '1 ano', 'Coelho', 'Angorá', 'Pequeno', 'Fêmea', false, 3),
                                                                                                   ('Spike', '5 anos', 'Cachorro', 'Rottweiler', 'Grande', 'Macho', true, 4),
                                                                                                   ('Nina', '8 meses', 'Gato', 'Bengala', 'Pequeno', 'Fêmea', false, 4);

INSERT INTO voluntario (pessoa_id, abrigo_id) VALUES
                                                  (1, 1),
                                                  (2, 2),
                                                  (3, 3),
                                                  (4, 4);
