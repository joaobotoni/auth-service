# Spring Boot - API REST com JWT e Spring Security

Este é um projeto  desenvolvido utilizando o **Spring Boot**, com integração de **Spring Security** e **JWT** (JSON Web Token) para a autenticação e autorização de usuários. A aplicação é uma API REST para gerenciamento de usuarios, incluindo funcionalidades como validação de usuários e cadastro.

## Estrutura do Projeto

O projeto é estruturado de forma modular, seguindo boas práticas de desenvolvimento, e está organizado em pacotes para facilitar a manutenção e escalabilidade. Abaixo, está a descrição das principais pastas e suas responsabilidades:

- `controllers`: Controladores responsáveis pela lógica de roteamento e exposição das APIs para o cliente.
- `models/entities/dtos`: Contém as classes de entidades (representando as tabelas no banco de dados), modelos de dados (DTOs) e classes de transferência de dados.
- `enums`: Contém as definições de enums, como os tipos de status de pedidos, tipos de usuários, entre outros.
- `settings/security`: Contém as configurações de segurança, como o gerenciamento de tokens JWT, configuração do Spring Security e detalhes de autenticação e autorização.
- `services`: Camada de serviço que contém a lógica de negócios e interage com os repositórios.
- `repositories`: Interface que define os métodos de acesso aos dados, utilizando Spring Data JPA para comunicação com o banco de dados.
- `resources`: Arquivos de configuração `application.properties` 

## Funcionalidades Implementadas

- **Cadastro e autenticação de usuários** utilizando JWT e Spring Security.
- **Proteção de rotas** com base em papéis de usuário (admin, user, etc.).
- **Cadastro de produtos**, gerenciamento de estoques e realização de pedidos.
- **Desabilitação do CSRF** (Cross-site request forgery), já que estamos utilizando autenticação baseada em token e não formulários tradicionais.
- **Persistência de dados** com PostgreSQL em produção e H2 para testes.
- **Padrão ORM** utilizando **JPA** e **Hibernate** para mapeamento objeto-relacional.
- **Arquitetura Stateless**, onde a autenticação e a autorização são realizadas com JWT, sem a necessidade de sessões no servidor.

## Tecnologias Utilizadas

![Static Badge](https://img.shields.io/badge/Spring-white?logo=spring&color=%23232F3E)
![Static Badge](https://img.shields.io/badge/Spring_Security-white?logo=springsecurity&color=%23232F3E)
![Static Badge](https://img.shields.io/badge/JWT-white?logo=jsonwebtokens&color=%23232F3E)
![Static Badge](https://img.shields.io/badge/-Maven-232F3E?style=flat&logo=apachemaven&logoColor=C71A36)
![Static Badge](https://img.shields.io/badge/Hibernate-white?logo=hibernate&color=%23232F3E)
![Static Badge](https://img.shields.io/badge/-PostgreSQL-232F3E?style=flat&logo=postgresql)




## Configuração de Segurança

A aplicação utiliza **Spring Security** para configurar a autenticação e autorização de usuários. O gerenciamento de sessão é feito através de **JWT**, onde os tokens são gerados no momento do login e devem ser enviados pelo cliente em todas as requisições subsequentes para garantir o acesso.

### Detalhes da Implementação:

- **CSRF desabilitado**: A proteção contra CSRF foi desabilitada, pois estamos utilizando tokens JWT para autenticação e não formulários tradicionais.
- **Autenticação**: O processo de login gera um **JWT** que deve ser enviado pelo cliente nas requisições subsequentes, no cabeçalho **Authorization**.
- **Authorization**: As rotas podem ser protegidas por papéis (roles) definidos no JWT, permitindo acesso apenas para usuários autenticados e autorizados.

### Configuração do JWT

- **Token de acesso**: O JWT é gerado a partir das credenciais do usuário no momento do login e possui um tempo de expiração configurável.
- **Armazenamento de token**: O token é enviado em cada requisição subsequente via cabeçalho `Authorization` no formato `Bearer <token>`.
- **Algoritmo**: O algoritmo utilizado para assinatura do token é o **HMAC256**.

## Banco de Dados

O projeto utiliza **PostgreSQL** como banco de dados principal para persistência dos dados em produção. Durante o desenvolvimento e testes, utilizamos o **H2 Database**, que é um banco de dados em memória, para facilitar o processo de testes e evitar dependências externas no ambiente de desenvolvimento.

- **PostgreSQL**: Usado para produção.
- **H2 Database**: Usado para testes (modo em memória).

### Configuração do Banco de Dados

No arquivo `application.properties` as configurações do banco de dados estão definidas conforme abaixo:

```properties
# datasource
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# jpa
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true

