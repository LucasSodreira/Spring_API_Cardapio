# Cardápio API

Uma API REST simples para gerenciamento de cardápios de comida, desenvolvida com Spring Boot e PostgreSQL.

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**

## 📋 Pré-requisitos

- Java 21 ou superior
- PostgreSQL instalado e rodando
- Maven (ou use o wrapper incluído)

## 🚀 Como executar

### 1. Configure o banco de dados

Crie o banco de dados no PostgreSQL:

```sql
CREATE DATABASE food;
```

### 2. Configure as credenciais

As configurações estão em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/food
spring.datasource.username=postgres
spring.datasource.password=root
```

Ajuste `username` e `password` conforme sua instalação do PostgreSQL.

### 3. Execute a aplicação

```bash
# Usando Maven wrapper (recomendado)
./mvnw spring-boot:run

# Ou usando Maven instalado
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

## 📡 Endpoints da API

### Listar todos os pratos
```http
GET /food
```

**Resposta:**
```json
[
  {
    "id": 1,
    "title": "Pizza Margherita",
    "image": "pizza.jpg",
    "price": 2500
  }
]
```

### Adicionar novo prato
```http
POST /food
Content-Type: application/json

{
  "title": "Pizza Margherita",
  "image": "pizza.jpg",
  "price": 2500
}
```

## 🏗️ Estrutura do Projeto

```
src/main/java/com/example/cardapio/
├── CardapioApplication.java     # Classe principal
├── controller/
│   └── FoodController.java      # Endpoints REST
└── food/
    ├── Food.java                # Entidade JPA
    ├── FoodRepository.java      # Repository
    ├── FoodRequestDTO.java      # DTO para requisições
    └── FoodResponseDTO.java     # DTO para respostas
```

## 📊 Modelo de Dados

### Tabela `foods`

| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | BIGINT | Chave primária (auto-incremento) |
| title | VARCHAR | Nome do prato |
| image | VARCHAR | URL/nome da imagem |
| price | INTEGER | Preço em centavos |

## 🛠️ Desenvolvimento

### Executar testes
```bash
./mvnw test
```

### Compilar
```bash
./mvnw clean compile
```

### Gerar JAR
```bash
./mvnw clean package
```

## 📝 Observações

- O preço é armazenado em **centavos** (ex: R$ 25,00 = 2500)
- As tabelas são criadas automaticamente pelo Hibernate
- CORS está habilitado para todas as origens (adequado apenas para desenvolvimento)

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para detalhes.
