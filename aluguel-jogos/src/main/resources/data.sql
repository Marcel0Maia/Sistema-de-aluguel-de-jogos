-- Usuários
INSERT INTO USUARIO (nome, email, senha, data_nascimento, is_admin) VALUES ('João Silva', 'joao.silva@example.com', '123456', '1990-01-01', true);
INSERT INTO USUARIO (nome, email, senha, data_nascimento, is_admin) VALUES ('Maria Oliveira', 'maria.oliveira@example.com', 'senhaSegura2025', '1988-07-15', false);
INSERT INTO USUARIO (nome, email, senha, data_nascimento, is_admin) VALUES ('Carlos Souza', 'carlos.souza@example.com', 'abc123', '1995-03-22', false);
INSERT INTO USUARIO (nome, email, senha, data_nascimento, is_admin) VALUES ('Ana Paula', 'ana.paula@example.com', 'minhaSenha2025', '1992-11-05', false);
INSERT INTO USUARIO (nome, email, senha, data_nascimento, is_admin) VALUES ('Pedro Henrique', 'pedro.henrique@example.com', 'pedro@2025', '2000-09-10', false);
INSERT INTO USUARIO (nome, email, senha, data_nascimento, is_admin) VALUES ('Marcelinho Juju', 'marcelo.juju@example.com', 'marcelo', '2000-09-15', false);


-- Jogos
INSERT INTO JOGO (nome, descricao, requisitos_sistema, desenvolvedor, publicador, genero, preco, imagem_url, em_destaque, no_carrossel)
VALUES ('The Witcher 3', 'RPG de mundo aberto', 'Intel i5, 8GB RAM, GTX 960', 'CD Projekt', 'CD Projekt', 'RPG', 99.90, 'https://meusite.com/imagens/the-witcher-3.jpg', false, false);


INSERT INTO JOGO (nome, descricao, requisitos_sistema, desenvolvedor, publicador, genero, preco, imagem_url, em_destaque, no_carrossel)
VALUES ('Cyberpunk 2077', 'Futuro distópico em mundo aberto', 'Intel i7, 16GB RAM, RTX 2060', 'CD Projekt RED', 'CD Projekt RED', 'Ação/RPG', 199.90, 'https://meusite.com/imagens/cyberpunk-2077.jpg', false, false);


INSERT INTO JOGO (nome, descricao, requisitos_sistema, desenvolvedor, publicador, genero, preco, imagem_url, em_destaque, no_carrossel)
VALUES ('Elden Ring', 'RPG de ação em mundo aberto', 'Intel i7, 16GB RAM, RTX 2070', 'FromSoftware', 'Bandai Namco', 'RPG de ação', 249.90, 'https://meusite.com/imagens/elden-ring.jpg', false, false);


