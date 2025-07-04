# Sistema de Aluguel de Jogos

Este projeto é uma aplicação **Spring Boot** que oferece um CRUD completo para gerenciamento de usuários e jogos, com banco de dados H2 em modo persistente. O objetivo é permitir que qualquer membro da equipe configure, execute e teste o sistema facilmente.

---

## Visão Geral

- **Framework:** Spring Boot 3.4.5  
- **Java:** 17+ (⚠️ O `pom.xml` também define `maven.compiler.source/target` como 20 — ajuste se necessário)  
- **Banco:** H2 persistente (`./data/aluguel-jogos-db`)  
- **API:** Endpoints RESTful  

---

## Principais Dependências

| Dependência                      | Função                                                |
|----------------------------------|-------------------------------------------------------|
| **spring-boot-starter-web**      | Criação e exposição dos endpoints REST               |
| **spring-boot-starter-data-jpa** | Mapeamento objeto-relacional com JPA/Hibernate       |
| **H2 Database**                  | Banco relacional em arquivo local                    |
| **spring-boot-starter-test**     | Testes automatizados (JUnit, Mockito)                |

---

## Como Configurar o Projeto Localmente

### 1. Clonar o repositório

```bash
git clone <URL-do-repositório>
```

---

### 2. Abrir na IDE

Recomenda-se usar **IntelliJ IDEA** ou qualquer IDE compatível com Maven e Java 17+.

---

### 3. Verificar as configurações do banco

Arquivo:

```
src/main/resources/application.properties
```

Principais parâmetros:

```
spring.datasource.url=jdbc:h2:file:./data/aluguel-jogos-db
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

- **Modo persistente:** o banco salva os dados entre execuções.
- **Console do H2 ativo.**

---

### 4. Rodar a aplicação

- No IntelliJ, abra `AluguelJogosApplication.java` e clique em **Run**.
- A aplicação estará disponível em:
  ```
  http://localhost:8080
  ```

---

## Banco de Dados e Dados de Teste

### Acessar o H2 Console

Abra no navegador:

```
http://localhost:8080/h2-console
```

Preencha:

| Campo     | Valor                                |
|-----------|--------------------------------------|
| JDBC URL  | jdbc:h2:file:./data/aluguel-jogos-db |
| User Name | sa                                   |
| Password  | (em branco)                          |

Clique em **Connect**.

---

### Popular dados de exemplo

O projeto inclui um arquivo `data.sql` com inserts de **usuários e jogos**.

Por padrão, **ele NÃO executa automaticamente** com `ddl-auto=update`. Para carregar automaticamente:

1. Altere no `application.properties`:

```
spring.jpa.hibernate.ddl-auto=create
spring.sql.init.mode=always
```

⚠️ Isso **apaga todos os dados** a cada execução e recria com os dados do `data.sql`.

2. Ou, carregue manualmente no H2 Console:
   - Copie o conteúdo de `src/main/resources/data.sql`.
   - Cole na aba **SQL** e clique em **Run**.

---

## Testar a API

Use ferramentas como **Postman** ou **cURL** para interagir com os endpoints.

### Principais Rotas

| Método | Endpoint     | Descrição                   |
|--------|--------------|-----------------------------|
| GET    | /usuarios    | Lista todos os usuários     |
| POST   | /usuarios    | Cadastra um novo usuário    |
| GET    | /jogos       | Lista todos os jogos        |
| POST   | /jogos       | Cadastra um novo jogo       |

---

### Exemplo de JSON – Criar Usuário

```json
{
  "nome": "Teste",
  "email": "teste@example.com",
  "senha": "123456"
}
```

---

### Exemplo de JSON – Criar Jogo

```json
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
```

---

## Executar Testes Automatizados

O projeto contém testes automatizados em:

```
src/test/java/projeto/aluguel_jogos/BibliotecaControllerTest.java
```

Estes testes validam o funcionamento dos endpoints e cenários de CRUD.

### Como executar os testes

**1. Pela IDE (IntelliJ IDEA ou Eclipse)**

- Navegue até o arquivo de teste.
- Clique com o botão direito em `BibliotecaControllerTest.java`.
- Selecione **Run 'BibliotecaControllerTest'**.
- Os resultados aparecerão no console da IDE.

**2. Pelo terminal com Maven**

Na raiz do projeto, execute:

```bash
mvn test
```

O Maven compilará e executará todos os testes. Se tudo estiver correto, aparecerá:

```
BUILD SUCCESS
```

---

## Considerações Importantes

- Para compatibilidade com Java 17, ajuste o `pom.xml`:

```xml
<maven.compiler.source>17</maven.compiler.source>
<maven.compiler.target>17</maven.compiler.target>
```

---

## Dúvidas?

Consulte a documentação oficial do Spring Boot ou alguém do projeto.
