
# TODO List

Uma aplicação Spring Boot projetada para gerenciar tarefas (CRUD) com autenticação básica, demonstrando práticas modernas de desenvolvimento, tratamento de exceções e deployment simplificado, ideal para ambientes como o site Render.

## Configuração

O arquivo `application.properties` está configurado para uso do banco de dados em memória H2, facilitando testes e desenvolvimento local sem necessidade de configurações externas de banco de dados.

```properties
spring.application.name=todolist
spring.datasource.url=jdbc:h2:mem:todolist
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

Acesse a console H2 em `http://localhost:8080/h2-console` para visualizar e gerenciar o banco de dados em memória.

## Tecnologias Utilizadas

- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **SpringDoc OpenAPI**
- **H2 Database**
- **Lombok**
- **BCrypt**
- **Docker** (O arquivo Dockerfile foi criado para possíveis usos futuros ou deployments em ambientes que suportam Docker, como o site Render.)

## Como Executar

1. Clone o repositório:

   ```
   git clone https://github.com/gaabrielgr/todolist.git
   cd todolist
   ```

2. Execute o projeto com Maven:

   ```
   ./mvnw spring-boot:run
   ```

## Arquitetura do Projeto

### Gerenciamento de Usuários

- **`UserModel`**
- **`UserController`**
- **`IUserRepository`**

### Gerenciamento de Tarefas

- **`TaskModel`**
- **`TaskController`**
- **`ITaskRepository`**

## Funcionalidades Principais

- Criação, listagem, atualização e remoção de tarefas.
- Autenticação de usuários.

