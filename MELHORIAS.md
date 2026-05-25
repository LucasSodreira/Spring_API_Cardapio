# Melhorias sugeridas para o Back-end

## O que já está bom

- Projeto Spring Boot organizado em camadas: controller, service, repository.
- Validação com `jakarta.validation` em `FoodRequestDTO`.
- Tratamento global de exceções em `GlobalExceptionHandler`.
- Uso de JPA com `FoodRepository` para CRUD simples.

## O que dá para melhorar sem complicar muito

### 1. Usar variáveis de ambiente para dados de conexão
O `application.properties` guarda usuário e senha do banco diretamente. Para produção ou outros ambientes, é melhor usar:
- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

via `SPRING_DATASOURCE_*` ou perfis `application-dev.properties`.

### 2. Definir mapeamento base mais claro
O `FoodController` usa `@RequestMapping("food")`. Uma boa prática é usar algo como `/api/food` para deixar claro que é API.

### 3. Adicionar `@Transactional` no serviço
Para operações de atualização e exclusão, é interessante garantir transações no service:
- `@Transactional(readOnly = true)` em buscas
- `@Transactional` em criação/atualização/exclusão

### 4. Atualização de entidade mais robusta
No `updateFood`, você cria uma nova entidade com `new Food(...)`. Isso funciona, mas pode ser melhor usar setters ou um método de atualização na entidade para preservar atalho do JPA e evitar problemas com relacionamentos futuros.

### 5. Endpoints extras simples
Você já tem CRUD completo. Se quiser um pouco mais de utilidade leve:
- buscar por nome (`GET /food?search=...`)
- ordenar por preço (
`GET /food?sort=price,asc`
)
- paginação com `Pageable`

Não é obrigatório, mas ajuda o front quando o cardápio crescer.

### 6. Documentar como rodar o backend
Crie um `README.md` simples na pasta `Spring_API_Cardapio` com instruções:
- `./mvnw spring-boot:run`
- como testar a API
- porta padrão

### 7. Considerar migração de schema no futuro
Você usa `spring.jpa.hibernate.ddl-auto=update`, que é ok para dev. Para algo mais seguro, depois pode evoluir para `Flyway` ou `Liquibase`.

## Sugestões extras opcionais

- Adicionar testes de integração com `@SpringBootTest` para o controller/serviço.
- Usar DTOs separados também para request e response já está correto; manter assim.
- Se quiser um nível extra leve, adicione `spring.jpa.show-sql=true` apenas em `application-dev.properties`.

## Prioridade recomendada

1. Documentar como rodar (README)
2. Mover dados sensíveis de `application.properties` para ambiente
3. Ajustar `updateFood` para usar setters ou método de entidade
4. Usar prefixo de rota `/api/food`
5. Adicionar paginação/busca leve se o catálogo crescer
