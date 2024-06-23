# Catálogo de Livros

Este projeto é um catálogo de livros que oferece interação textual via console com os usuários.
Utiliza a API Gutendex para buscar informações sobre livros e autores.

## Funcionalidades

- Buscar livro por título
- Listar todos os livros
- Listar livros por idioma
- Listar autores
- Listar autores vivos em determinado ano

## Configuração

1. Clone o repositório:
   ```bash
   git clone 
   cd catalogo-livros

spring.datasource.url=jdbc:postgresql://localhost:5432/catalogo_livros
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

./mvnw spring-boot:run