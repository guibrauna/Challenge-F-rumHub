# Challenge-F-rumHub
FórumHub é uma API RESTful desenvolvida em Java com Spring Boot que permite o gerenciamento de tópicos em um fórum de discussão.

## ✅ Funcionalidades

- Criar um novo tópico (`POST /topicos`)
- Listar todos os tópicos (`GET /topicos`)
- Consultar um tópico específico (`GET /topicos/{id}`)
- Atualizar um tópico (`PUT /topicos/{id}`)
- Deletar um tópico (`DELETE /topicos/{id}`)
- Autenticação de usuários via JWT (`POST /auth/login`)

---

## 🔒 Segurança

A autenticação é feita por meio de tokens JWT. Após o login, você deve incluir o token no header Authorization em todas as requisições protegidas.

**Exemplo:**
---

## 💻 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- JWT (Auth0)
- MySQL
- Maven

---

## ⚙️ Configuração do Banco de Dados

Crie um banco chamado `forumhub` no seu MySQL e configure seu `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forumhub
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

jwt.secret=minha-chave-secreta
jwt.expiration=86400000
🚀 Como Executar

    Clone o repositório:
git clone https://github.com/seu-usuario/forumhub.git
cd forumhub
Compile o projeto:
mvn clean install
Rode a aplicação:
mvn spring-boot:run

Requisição de Login

Endpoint:
POST /auth/login
Exemplo de JSON:
{
  "email": "usuario@email.com",
  "senha": "123456"
}

Resposta:
{
  "token": "jwt-gerado"
}

🧑‍💻 Autor

Projeto desenvolvido como parte do Challenge Back-End no curso da Alura.
