Projeto Aluguel de Jogos

✅ Visão geral

Este projeto consiste em uma aplicação Spring Boot com CRUD de aluguel de jogos, utilizando o banco de dados H2 em modo persistente. O sistema está preparado para ser clonado, configurado e executado facilmente por qualquer membro da equipe para dar continuidade ao desenvolvimento.

✅ Dependências principais

Spring Boot Starter Web: Facilita a criação de aplicações web, incluindo RESTful APIs.

Spring Boot Starter Data JPA: Simplifica a integração com o JPA e Hibernate para persistência de dados.

H2 Database: Banco de dados relacional em memória ou persistente para ambientes de desenvolvimento e testes.

Spring Boot Starter Test: Ferramentas para testes automatizados (JUnit, Mockito, etc.).

📦 Como as dependências ajudam:

Spring Boot Starter Web → cria e expõe os endpoints da aplicação.

Spring Boot Starter Data JPA → simplifica o CRUD através dos repositórios.

H2 → permite que cada dev tenha seu banco local sem necessidade de instalação.

Starter Test → facilita a criação de testes automatizados.

✅ Como configurar o projeto localmente

Clonar o repositório:

git clone <URL-do-repositório>

Abrir o projeto:

Use o IntelliJ IDEA ou outra IDE compatível com Java 17+ e Maven.

Verifique as configurações do banco:

No arquivo src/main/resources/application.properties:

spring.datasource.url=jdbc:h2:file:./data/aluguel-jogos-db
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update

➡️ Com update, os dados não são apagados entre execuções.

Rodar a aplicação:

No IntelliJ → abra AluguelJogosApplication.java → clique na seta verde → Run.

A aplicação estará disponível em:

http://localhost:8080

✅ Como acessar e usar o banco de dados

📥 Acessar o H2 Console

Abra no navegador:

http://localhost:8080/h2-console

Preencha os campos:

Campo

Valor

JDBC URL

jdbc:h2:file:./data/aluguel-jogos-db

Username

sa

Password

(em branco)

Clique em Connect.

📥 Como popular o banco de dados

➡️ O arquivo src/main/resources/data.sql contém comandos INSERT prontos para popular o banco.➡️ Por padrão, com ddl-auto=update, o data.sql não é executado automaticamente.

✅ Como inserir os dados:

Opção 1: Manualmente via H2 Console:

Acesse a aba SQL.

Cole os comandos do data.sql.

Execute clicando em "Run" ou Ctrl + Enter.

Opção 2: Automaticamente:

Alterar a configuração para:

spring.jpa.hibernate.ddl-auto=create
spring.sql.init.mode=always

Com isso, o banco será recriado e populado automaticamente com os dados do data.sql a cada execução.

⚠️ Cuidado: com create, todos os dados serão apagados e recriados sempre que a aplicação iniciar.

✅ Como testar a API

Usando ferramentas como Postman ou cURL, testar os seguintes endpoints:

Método		URL		 FUNÇÃO

GET		/usuários	Lista todos usuários

POST		/usuários	Cadastra novo usuário

GET		/jogos		Lista todos os jogos

POST		/jogos		Cadastra novo jogo

/usuarios


Exemplo de JSON para criar um usuário:

{
  "nome": "Teste",
  "email": "teste@example.com",
  "senha": "123456"
}

Exemplo de JSON para criar um jogo:

{
  "nome": "Novo Jogo",
  "descricao": "Jogo incrível",
  "requisitosSistema": "Intel i5, 8GB RAM",
  "desenvolvedor": "Dev Teste",
  "publicador": "Pub Teste",
  "genero": "Ação",
  "preco": 99.90,
  "imagemUrl": "https://teste.com/imagem.jpg"
}

✅ Considerações finais

Cada desenvolvedor deve manter o banco local.

O arquivo data.sql facilita a padronização dos dados de teste.

O arquivo .gitignore já evita o versionamento do banco local.

✅ Qualquer dúvida, consulte a documentação ou fale com o responsável do backend.