# 🧠 ForumHub API

API REST construída com **Spring Boot 3**, **JWT**, **Flyway**, **MySQL** e **Swagger** para gerenciamento de tópicos e respostas em fóruns de discussão.

---

## 📋 Sumário

- [🚀 Tecnologias](#-tecnologias)
- [📁 Estrutura de Pastas](#-estrutura-de-pastas)
- [⚙️ Requisitos](#️-requisitos)
- [🏁 Como Executar](#-como-executar)
- [🔐 Autenticação JWT](#-autenticação-jwt)
- [📡 Documentação Swagger](#-documentação-swagger)
- [📌 Funcionalidades](#-funcionalidades)
- [📦 Migrations (Flyway)](#-migrations-flyway)
- [🧪 Testes](#-testes)
- [🧑‍💻 Autor](#-autor)

---

## 🚀 Tecnologias

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

## 📁 Estrutura de Pastas

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

## ⚙️ Requisitos

- Java 17 ou superior
- Maven
- MySQL 8+
- IDE (IntelliJ, VS Code ou Eclipse)

---

## 🏁 Como Executar

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

## 🔐 Autenticação JWT

- Realize login via endpoint `/login`:
  ```json
  {
    "login": "admin",
    "senha": "123456"
  }
  ```
- Receba um token JWT.
- Envie nas requisições protegidas com:
  ```http
  Authorization: Bearer SEU_TOKEN
  ```

---

## 📡 Documentação Swagger

Acesse via navegador:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📌 Funcionalidades

- ✅ Cadastro e login de usuários
- ✅ Proteção de rotas com JWT
- ✅ CRUD completo de tópicos
- ✅ CRUD de respostas relacionadas a tópicos
- ✅ Validação com Bean Validation
- ✅ Integração com banco MySQL
- ✅ Migração de schema com Flyway
- ✅ Documentação automática com Swagger

---

## 📦 Migrations (Flyway)

Migrations automáticas ao subir a aplicação:

```sql
# src/main/resources/db/migration/V1__criar_tabelas_iniciais.sql

CREATE TABLE usuarios (...);
CREATE TABLE topicos (...);
CREATE TABLE respostas (...);
```

---

## 🧪 Testes

Você pode usar ferramentas como **Postman** ou **Insomnia** para testar os endpoints ou usar diretamente o Swagger.

---

## 🧑‍💻 Autor

Desenvolvido por **André Azevedo de  Oliveira**


> Este projeto foi desenvolvido com fins educacionais e profissionais, simulando um fórum de discussões com autenticação segura e arquitetura moderna.
