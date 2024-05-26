# Contas API

## Desenvolvedor
Marco Antonio de Souza Duarte

## Objetivo
Esta API REST foi desenvolvida como um exemplo de desafio pessoal. O objetivo é fornecer um sistema simples de contas a pagar, com operações de CRUD, alteração de situação de pagamento, consulta de contas e importação de contas a partir de um arquivo CSV. O projeto também implementa autenticação de segurança, migrações de banco de dados com Flyway, e é executável via Docker e Docker Compose.

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.3.0
- Spring Security
- Spring Data JPA
- PostgreSQL
- Flyway
- Docker
- Docker Compose
- Swagger (SpringDoc OpenAPI)
- Apache Commons CSV
- Lombok

## Configuração do Ambiente

### Requisitos
- JDK 17 ou superior
- Maven 3.6.0 ou superior
- Docker
- Docker Compose

### Configuração do Banco de Dados
Certifique-se de que o PostgreSQL esteja configurado corretamente. Utilize as seguintes credenciais de acesso:

- **URL**: jdbc:postgresql://localhost:5432/contas
- **Usuário**: postgres
- **Senha**: 123456

## Autenticação
A API implementa autenticação básica. As credenciais padrão são:
- **Usuário**: `usuarioContas`
- **Senha**: `senhaContas`

## Execução do Projeto

### 1. Construção do Projeto
Clone o repositório e navegue até o diretório do projeto:

```bash
git clone https://github.com/masouzaduarte/contas.git
cd contas
mvn clean install
```

### 2. Execução com Docker
- Crie uma rede Docker:
```bash
-docker network create contas
```

- Inicie os contêineres:
```bash
-docker-compose up
```

### Acessando a API
Após a inicialização bem-sucedida, a API estará acessível em `http://localhost:8080`.

### Documentação da API
A documentação da API está disponível através do Swagger em `http://localhost:8080/swagger-ui.html`.

## Estrutura do Projeto
- `src/main/java/org/pessoal/contas/config`: Configurações de segurança e outras configurações.
- `src/main/java/org/pessoal/contas/controller`: Controladores REST.
- `src/main/java/org/pessoal/contas/model`: Entidades JPA.
- `src/main/java/org/pessoal/contas/repository`: Repositórios JPA.
- `src/main/java/org/pessoal/contas/service`: Serviços de negócio.
- `src/test/java/org/pessoal/contas`: Testes unitários e de integração.

## Considerações Finais
Este projeto foi desenvolvido como um desafio técnico para demonstrar habilidades em desenvolvimento de APIs RESTful utilizando Spring Boot, segurança com Spring Security, migrações de banco de dados com Flyway e containerização com Docker.

Agradeço a oportunidade de realizar este desafio e espero que o projeto atenda às expectativas.

---

Marco Antonio de Souza Duarte
