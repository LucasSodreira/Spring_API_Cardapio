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

## � Funcionalidades Robustas

### ✅ Implementadas:
- **Validação de dados** com Bean Validation
- **Tratamento global de exceções** com mensagens personalizadas
- **Camada de serviço** para lógica de negócio
- **Códigos HTTP apropriados** (201, 404, 400, etc.)
- **Configuração CORS** mais segura
- **Testes unitários** com Mockito
- **Estrutura em camadas** (Controller → Service → Repository)

### �📡 Endpoints da API

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

### Buscar prato por ID
```http
GET /food/{id}
```

**Resposta (200):**
```json
{
  "id": 1,
  "title": "Pizza Margherita",
  "image": "pizza.jpg",
  "price": 2500
}
```

**Resposta (404):** Prato não encontrado

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

**Resposta (201):**
```json
{
  "id": 1,
  "title": "Pizza Margherita", 
  "image": "pizza.jpg",
  "price": 2500
}
```

**Resposta (400):** Dados inválidos
```json
{
  "status": 400,
  "message": "Dados inválidos",
  "timestamp": "2025-08-03T18:30:00",
  "errors": {
    "title": "Título é obrigatório",
    "price": "Preço deve ser maior que zero"
  }
}
```

### Atualizar prato existente
```http
PUT /food/{id}
Content-Type: application/json

{
  "title": "Pizza Margherita Especial",
  "image": "pizza-especial.jpg",
  "price": 3000
}
```

**Resposta (200):**
```json
{
  "id": 1,
  "title": "Pizza Margherita Especial",
  "image": "pizza-especial.jpg",
  "price": 3000
}
```

**Resposta (404):** Prato não encontrado

### Excluir prato
```http
DELETE /food/{id}
```

**Resposta (204):** Prato excluído com sucesso  
**Resposta (404):** Prato não encontrado

## 🏗️ Estrutura do Projeto

```
src/main/java/com/example/cardapio/
├── CardapioApplication.java      # Classe principal
├── config/
│   └── CorsConfig.java           # Configuração CORS
├── controller/
│   └── FoodController.java       # Endpoints REST
├── service/
│   └── FoodService.java          # Lógica de negócio
├── exception/
│   ├── GlobalExceptionHandler.java  # Tratamento global de erros
│   ├── FoodNotFoundException.java   # Exceção customizada
│   ├── ErrorResponse.java           # Estrutura de resposta de erro
│   └── ValidationErrorResponse.java # Estrutura de erro de validação
└── food/
    ├── Food.java                 # Entidade JPA
    ├── FoodRepository.java       # Repository
    ├── FoodRequestDTO.java       # DTO para requisições (com validações)
    └── FoodResponseDTO.java      # DTO para respostas
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
- CORS está configurado para permitir todas as origens em desenvolvimento

## 🐛 Troubleshooting

### Erro 500 - "Erro interno do servidor"
Se você receber um erro 500, verifique:
1. **PostgreSQL está rodando** e acessível
2. **Banco `food` existe** no PostgreSQL
3. **Credenciais corretas** no `application.properties`
4. **Logs da aplicação** para detalhes específicos

### Erro de CORS
Se tiver problemas de CORS:
- A configuração está em `CorsConfig.java`
- Para desenvolvimento, permite todas as origens
- Para produção, configure origens específicas

### Erro de conexão com banco
```bash
# Verifique se o PostgreSQL está rodando
pg_ctl status

# Teste a conexão
psql -U postgres -h localhost -d food
```

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para detalhes.
