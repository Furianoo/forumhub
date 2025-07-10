# ForumHub API

API REST construída com Spring Boot 3, JWT, Flyway, MySQL e Swagger para gerenciamento de tópicos e respostas em fóruns de discussão.

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring Security
- Spring Data JPA
- JWT (Auth0)
- MySQL 8+
- Flyway
- Lombok
- Swagger (springdoc-openapi)

---

## Estrutura do Projeto

```
forumhub/
├── src/
│   ├── main/
│   │   ├── java/com/forumhub/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   ├── config/
│   │   │   └── ForumhubApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/V1__criar_tabelas_iniciais.sql
└── pom.xml
```

---

## Requisitos

- Java 17 ou superior
- Maven
- MySQL 8 ou superior
- IDE (IntelliJ IDEA, VS Code, Eclipse, etc.)

---

## Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/forumhub.git
cd forumhub
```

2. Compile e rode a aplicação:

```bash
mvn clean package
mvn spring-boot:run
```

---

## Autenticação JWT

Para autenticar e obter um token JWT, faça uma requisição POST para o endpoint `/login` com o seguinte JSON no corpo da requisição:

```json
{
  "login": "usuario",
  "senha": "senha123"
}
```

Você receberá um token JWT que deve ser enviado no header das requisições protegidas:

```
Authorization: Bearer SEU_TOKEN_AQUI
```

---

## Documentação da API (Swagger)

A documentação automática está disponível em:

```
http://localhost:8080/swagger-ui/index.html
```

Lá você pode visualizar e testar todos os endpoints da API.

---

## Funcionalidades

- Cadastro e login de usuários
- Proteção de rotas com autenticação JWT
- CRUD completo para tópicos
- CRUD para respostas relacionadas a tópicos
- Validação de dados com Bean Validation (`@Valid`)
- Integração com banco MySQL para persistência dos dados
- Migração automática do banco via Flyway
- Documentação automática com Swagger/OpenAPI

---

## Migrações (Flyway)

Ao iniciar a aplicação, o banco de dados será configurado automaticamente com base no script:

```
src/main/resources/db/migration/V1__criar_tabelas_iniciais.sql
```

---

## Testes

Recomenda-se o uso de ferramentas como [Postman](https://www.postman.com/), [Insomnia](https://insomnia.rest/) ou a própria interface Swagger UI para testar os endpoints da API.

---

## Autor

**André Azevedo de Oliveira**

Projeto para fins educacionais e profissionais, simulando um fórum de discussões seguro e moderno.
