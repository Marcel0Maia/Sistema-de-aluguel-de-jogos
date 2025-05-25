-- Usuários
INSERT INTO USUARIO (nome, email, senha, data_nascimento) VALUES ('João Silva', 'joao.silva@example.com', '123456', '1990-01-01');
INSERT INTO USUARIO (nome, email, senha, data_nascimento) VALUES ('Maria Oliveira', 'maria.oliveira@example.com', 'senhaSegura2025', '1988-07-15');
INSERT INTO USUARIO (nome, email, senha, data_nascimento) VALUES ('Carlos Souza', 'carlos.souza@example.com', 'abc123', '1995-03-22');
INSERT INTO USUARIO (nome, email, senha, data_nascimento) VALUES ('Ana Paula', 'ana.paula@example.com', 'minhaSenha2025', '1992-11-05');
INSERT INTO USUARIO (nome, email, senha, data_nascimento) VALUES ('Pedro Henrique', 'pedro.henrique@example.com', 'pedro@2025', '2000-09-10');

-- Jogos
INSERT INTO JOGO (nome, descricao, requisitos_sistema, desenvolvedor, publicador, genero, preco, imagem_url)
VALUES ('The Witcher 3', 'RPG de mundo aberto', 'Intel i5, 8GB RAM, GTX 960', 'CD Projekt', 'CD Projekt', 'RPG', 99.90, 'https://meusite.com/imagens/the-witcher-3.jpg');

INSERT INTO JOGO (nome, descricao, requisitos_sistema, desenvolvedor, publicador, genero, preco, imagem_url)
VALUES ('Cyberpunk 2077', 'Futuro distópico em mundo aberto', 'Intel i7, 16GB RAM, RTX 2060', 'CD Projekt RED', 'CD Projekt RED', 'Ação/RPG', 199.90, 'https://meusite.com/imagens/cyberpunk-2077.jpg');

INSERT INTO JOGO (nome, descricao, requisitos_sistema, desenvolvedor, publicador, genero, preco, imagem_url)
VALUES ('Elden Ring', 'RPG de ação em mundo aberto', 'Intel i7, 16GB RAM, RTX 2070', 'FromSoftware', 'Bandai Namco', 'RPG de ação', 249.90, 'https://meusite.com/imagens/elden-ring.jpg');

INSERT INTO JOGO (nome, descricao, requisitos_sistema, desenvolvedor, publicador, genero, preco, imagem_url)
VALUES ('Hades', 'Roguelike de ação', 'Intel i3, 4GB RAM', 'Supergiant Games', 'Supergiant Games', 'Roguelike', 49.90, 'https://meusite.com/imagens/hades.jpg');

INSERT INTO JOGO (nome, descricao, requisitos_sistema, desenvolvedor, publicador, genero, preco, imagem_url)
VALUES ('Minecraft', 'Jogo de construção e sobrevivência', 'Intel i3, 2GB RAM', 'Mojang', 'Mojang', 'Sandbox', 119.90, 'https://meusite.com/imagens/minecraft.jpg');
